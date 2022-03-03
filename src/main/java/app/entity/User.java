package app.entity;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "usr")
public class User implements UserDetailsService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "second_name", nullable = false)
    private String secondName;
    @Column(name = "age")
    private int age;
    @OneToOne
    @JoinColumn(name = "main_image_id")
    private Image mainImage;
    private List<Video> videoList;
    private List<Music> musicList;
    private List<Image> images;
    private List<User> friends;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
