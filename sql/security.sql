CREATE TABLE `permission`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `parent_id`   bigint                DEFAULT NULL COMMENT '父权限',
    `name`        varchar(64)  NOT NULL COMMENT '权限名称',
    `en_name`     varchar(64)  NOT NULL COMMENT '权限英文名称(代码指示)',
    `url`         varchar(255) NOT NULL COMMENT '授权路径',
    `remark`      varchar(255)          DEFAULT NULL COMMENT '备注',
    `status`      tinyint      NOT NULL DEFAULT '1' COMMENT '状态(0:无效,1:有效)',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='权限表';


CREATE TABLE `role`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(64) NOT NULL COMMENT '角色名称',
    `remark`      varchar(200)         DEFAULT NULL COMMENT '备注',
    `creator`     varchar(64)  DEFAULT NULL COMMENT '创建者',
    `last_operator` varchar(64) DEFAULT NULL COMMENT '最近的操作者',
    `status`      tinyint     NOT NULL DEFAULT '1' COMMENT '状态(0:无效,1:有效)',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';


CREATE TABLE `role_permission`
(
    `id`            bigint   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_id`       bigint   NOT NULL COMMENT '角色 ID',
    `permission_id` bigint   NOT NULL COMMENT '权限 ID',
    `status`        tinyint  NOT NULL DEFAULT '1' COMMENT '状态(0:无效,1:有效)',
    `create_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色权限表';

