package com.example.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.ColumnTransformer;

import com.example.board.util.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Users extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    private String email;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private int zipcode;
    
    private String steetAdr;
    
//    @ColumnTransformer(
//            read = "convert_from(decrypt(decode(detail_adr,'hex'),'ENC_KEY','aes'),'utf8')",
//            write = "encode(encrypt(convert_to(?,'utf8'),'ENC_KEY','aes'),'hex')"
//    )
    private String detailAdr;
    
    @Enumerated(EnumType.STRING)
    private RoleType role;    
    
    private String writer;
    
    @PrePersist
    public void writer() {
        this.writer = this.username;
    }

}
