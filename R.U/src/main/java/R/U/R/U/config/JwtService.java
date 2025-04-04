package R.U.R.U.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import io.github.cdimascio.dotenv.Dotenv;

//3
//4 Impotar dependencias jjwt-api , jjwt-impl , jjwt-jackson
@Service
public class JwtService {

    //Ocultamos la secret key
    private static String SECRET_KEY;

    public JwtService() {
        Dotenv dotenv = Dotenv.load();
        SECRET_KEY = dotenv.get("API_SECRET_KEY");
        if (SECRET_KEY == null || SECRET_KEY.isEmpty()) {
            throw new IllegalStateException("API_SECRET_KEY no está definida en el archivo .env");
        }
    }

    //Generamos el token
    //5
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        extraClaims.put("userRole", extractUserRole(userDetails)); // Agregar un campo extra () rol
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30 * 2)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    //Agregar el rol del usuario como un extra claim.
    private String extractUserRole(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User has no roles assigned"));
    }

    //4
    public static String getUserName(String token){
        return getClaim(token,Claims::getSubject);
    }

    public static String getUserRole(String token) {return getClaim(token, claims -> claims.get("userRole", String.class));}

    public static <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public static boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private static boolean isTokenExpired(String token) {return getExpiration(token).before(new Date());}

    private static Date getExpiration(String token) {return getClaim(token, Claims::getExpiration);}


}
