package org.slam.publicshare.book.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.common.domain.Auditable;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private boolean display;

    private Category(String name) {
        this.name = name;
        this.display = false;
    }

    public static Category of(String name) {
        return new Category(name);
    }

}
