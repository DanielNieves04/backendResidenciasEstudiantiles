package R.U.R.U.Service;

import R.U.R.U.Entity.EmailValuesDTO;
import R.U.R.U.Entity.User;
import R.U.R.U.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordResetService {

    @Autowired
    public EmailService emailService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    private final Map<String, ResetCodeData> resetCodes = new ConcurrentHashMap<>();

    private void storeResetCode(String email, String code) {
        resetCodes.put(email, new ResetCodeData(code, LocalDateTime.now().plusMinutes(15)));
    }

    public boolean sendPasswordResetCode(String email) {
        Optional<User> userOpt = userRepository.findUserByMail(email);
        if (!userOpt.isPresent()) {
            return false;
        }

        String code = generateRandomCode();
        storeResetCode(email, code);
        emailService.sendPasswordResetEmail(email, code);

        return true;
    }

    public boolean sendPasswordResetCodeLogin(String email) {
        Optional<User> userOpt = userRepository.findUserByMail(email);
        if (userOpt.isPresent()) {
            return false;
        }

        String code = generateRandomCode();
        storeResetCode(email, code);
        emailService.sendPasswordResetEmail(email, code);

        return true;
    }

    private String generateRandomCode() {
        Random random = new Random();
        return String.valueOf(1000 + random.nextInt(9000));
    }

    public boolean validateResetCode(String email, String code) {
        ResetCodeData resetData = resetCodes.get(email);
        if (resetData == null || resetData.expiration.isBefore(LocalDateTime.now())) {
            return false;
        }
        return resetData.code.equals(code);
    }

    public void changePassword(String email, String newPassword) {
        userRepository.findUserByMail(email).ifPresent(user -> {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            resetCodes.remove(email); // Eliminar el código después de usarlo
        });
    }

    private static class ResetCodeData {
        String code;
        LocalDateTime expiration;

        ResetCodeData(String code, LocalDateTime expiration) {
            this.code = code;
            this.expiration = expiration;
        }
    }

}
