package com.maja.sdamovieapp.user.entity;

import com.maja.sdamovieapp.movie.entity.MovieRates;
import com.maja.sdamovieapp.order.entity.Order;
import com.maja.sdamovieapp.user.enums.ClientTypeEnum;
import com.maja.sdamovieapp.user.enums.RoleNameEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapowanie bazodanowe użytkowników
 */

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(min = 6, max = 50)
    @Column(unique = true)
    private String login;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleNameEnum role;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "is_active")
    private boolean isActive;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientTypeEnum clientType;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MovieRates> rates = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<DeliveryAddress> addresses = new ArrayList<>();
}