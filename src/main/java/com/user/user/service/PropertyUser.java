package com.user.user.service;

import com.user.user.Entity.PropertyUserEntity;
import com.user.user.dto.PropertyUserDto;

import java.util.List;

public interface PropertyUser {

    public PropertyUserDto addPropertyUser(PropertyUserDto  dto);

    void deletePropertyUser(long propertyUserId);

    PropertyUserEntity updatePropertyUser(long propertyUserId, PropertyUserDto dto);

    List<PropertyUserDto> getPropertyUsers(int pageSize, int pageNo, String sortBy, String sortDir);

    PropertyUserEntity getPropertyUsersById(long propertyUserId);
}
