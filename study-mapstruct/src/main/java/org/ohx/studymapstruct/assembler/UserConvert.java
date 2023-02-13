package org.ohx.studymapstruct.assembler;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.ohx.studymapstruct.dto.UserDTO;
import org.ohx.studymapstruct.entity.User;

/**
 * @author mudkip
 * @date 2023/2/13
 */
@Mapper(componentModel = "spring")
@DecoratedWith(UserDecorator.class)
public interface UserConvert {
    UserDTO toDto(User user);
}
