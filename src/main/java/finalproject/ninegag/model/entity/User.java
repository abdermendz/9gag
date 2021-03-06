package finalproject.ninegag.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalproject.ninegag.model.dto.ReadyUserDTO;
import finalproject.ninegag.model.dto.RegisterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String userName;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private LocalDateTime dateRegistered;
    @ManyToMany(mappedBy = "upvoters",fetch = FetchType.EAGER)
    private List<Post> upvotedPosts = new ArrayList<>();
    @ManyToMany(mappedBy = "downvoters")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Post> downvotedPosts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.email, ((User) o).email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public User(RegisterUserDTO userDTO){
        setUserName(userDTO.getUsername());
        setFirstName(userDTO.getFirstName());
        setLastName(userDTO.getLastName());
        setEmail(userDTO.getEmail());
        setPassword(userDTO.getPassword());
        setDateRegistered(LocalDateTime.now());
    }

    public void setPassword(String password){
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public ReadyUserDTO toUserDTO(){
        ReadyUserDTO userDTO = new ReadyUserDTO();
        userDTO.setId(this.getId());
        userDTO.setUsername(this.getUserName());
        return userDTO;
    }

}
