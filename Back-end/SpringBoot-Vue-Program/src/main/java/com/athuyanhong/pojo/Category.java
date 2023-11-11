package com.athuyanhong.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;
    //主键ID
    @NotEmpty
    private String categoryName;
    //分类名称
    @NotEmpty
    private String categoryAlias;
    //分类别名
    @JsonIgnore
    private Integer createUser;
    //创建人ID
    private LocalDateTime createTime;
    //创建时间
    private LocalDateTime updateTime;
    //更新时间

    public interface Add extends Default {

    }
    public interface Update extends Default{

    }

}
