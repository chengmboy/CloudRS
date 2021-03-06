package io.github.chengmboy.cloudrs.uc.pojo.entity;

import io.github.chengmboy.cloudrs.common.web.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Role extends AbstractEntity {

    private String roleCode;

    private String name;

    @ManyToMany
    List<Permission> permissions;
}
