package com.user.user.service;

import com.user.user.Entity.PropertyUserEntity;
import com.user.user.Exception.ResourceNotFoundException;
import com.user.user.dto.PropertyUserDto;
import com.user.user.repository.PropertyUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyUserImpl  implements  PropertyUser{

    private PropertyUserRepository propertyUserRepository;

    public PropertyUserImpl(PropertyUserRepository propertyUserRepository) {
        this.propertyUserRepository = propertyUserRepository;
    }


    @Override
    public PropertyUserDto addPropertyUser(PropertyUserDto dto) {


        PropertyUserEntity entity = dtoToEntity(dto);
        PropertyUserEntity save = propertyUserRepository.save(entity);
        PropertyUserDto propertyUserDto = entityToDto(save);
        return propertyUserDto ;
    }
    public PropertyUserEntity dtoToEntity(PropertyUserDto dto){
        PropertyUserEntity entity=new PropertyUserEntity();
        entity.setName(dto.getName());
        entity.setMobile(dto.getMobile());
        entity.setEmailId(dto.getEmailId());

        return entity;
    }
    public PropertyUserDto entityToDto (PropertyUserEntity en){
        PropertyUserDto dto1=new PropertyUserDto();
        dto1.setId(en.getId());
        dto1.setName(en.getName());
        dto1.setMobile(en.getMobile());
        dto1.setEmailId(en.getEmailId());
        return dto1;
    }



    @Override
    public void deletePropertyUser(long propertyUserId) {
        propertyUserRepository.deleteById(propertyUserId);
    }
//UPDATE
    @Override
    public PropertyUserEntity updatePropertyUser(

            long propertyUserId,
            PropertyUserDto dto

    ) {
        Optional<PropertyUserEntity> byId = propertyUserRepository.findById(propertyUserId);
        PropertyUserEntity propertyUserEntity = byId.get();

        propertyUserEntity.setName(dto.getName());
        propertyUserEntity.setMobile(dto.getMobile());
        propertyUserEntity.setEmailId(dto.getEmailId());

        return propertyUserRepository.save(propertyUserEntity);

    }
//


    @Override
    public List<PropertyUserDto> getPropertyUsers
    (  int pageSize, int pageNo, String sortBy, String sortDir  ) {
        Pageable pageable = null;

        if(sortDir.equalsIgnoreCase("asc")){
            pageable=PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        } else if (sortDir.equalsIgnoreCase("desc") ){
            pageable=PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }



        Page<PropertyUserEntity> all = propertyUserRepository.findAll(pageable);
        List<PropertyUserEntity> pue = all.getContent();
        List<PropertyUserDto> pud = pue.stream().map(p -> entityToDto(p)).collect(Collectors.toList());
        return pud;
    }




    @Override
    public PropertyUserEntity  getPropertyUsersById(long propertyUserId) {
        PropertyUserEntity  pue =
                propertyUserRepository.findById(propertyUserId).orElseThrow(

                        ()-> new ResourceNotFoundException("User not found with id: "+propertyUserId)
        );
        return pue;
    }


}
