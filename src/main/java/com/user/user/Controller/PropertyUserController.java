package com.user.user.Controller;

import com.user.user.Entity.PropertyUserEntity;
import com.user.user.dto.PropertyUserDto;
import com.user.user.service.PropertyUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Rest API Class
@RequestMapping("/api/v1/propertyuser")
public class PropertyUserController {

    @Autowired
    private PropertyUser pu;

    public PropertyUserController(PropertyUser pu) {
        this.pu = pu;
    }

    //http://localhost:8080/api/v1/propertyuser/addPropertyUser

    @PostMapping ("/addPropertyUser")
    public ResponseEntity<?> addPropertyUser(

          @Valid @RequestBody  PropertyUserDto dto,
          BindingResult result

    ) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }

        PropertyUserDto propertyUserDto = pu.addPropertyUser(dto);
        return new ResponseEntity<>(propertyUserDto, HttpStatus.CREATED);

    }
//DELETE
    @DeleteMapping
    public ResponseEntity<String>deletePropertyUser(
           @RequestParam long propertyUserId
    ){
        pu.deletePropertyUser(propertyUserId);
        return new ResponseEntity<>("Record deleted successfully",HttpStatus.OK);
    }

    //UPDATE
    //http://localhost:8080/api/v1/propertyuser/1
@PutMapping("/{propertyUserId}")
    public <dto> ResponseEntity<PropertyUserEntity>updatePropertyUser(

            @PathVariable  long propertyUserId,
           @RequestBody PropertyUserDto dto

    ) {
    PropertyUserEntity propertyUser = pu.updatePropertyUser(propertyUserId, dto);
    return new ResponseEntity<>(propertyUser, HttpStatus.OK);

}
       //(Pagination Concept)
      //http://localhost:8080/api/v1/propertyuser?pageSize=3&pageNo=0

     //  (Sorting)
    // http://localhost:8080/api/v1/propertyuser?pageSize=3&pageNo=0&sortBy=name
    //http://localhost:8080/api/v1/propertyuser?pageSize=3&pageNo=0&sortBy=mobile
    //http://localhost:8080/api/v1/propertyuser?pageSize=3&pageNo=0&sortBy=emailId

    //( Sorting ascending order and descending order sorting )
    // http://localhost:8080/api/v1/propertyuser?pageSize=3&pageNo=0&sortBy=emailId&sortDir=desc

    //getmapping (list of students)
    @GetMapping
    public ResponseEntity<List<PropertyUserDto>> getPropertyUsers(
            @RequestParam(name = "pageSize", defaultValue = "5" , required = false)int pageSize,
            @RequestParam(name = "pageNo", defaultValue = "0" , required = false)int pageNo,
            @RequestParam(name = "sortBy", defaultValue = "id" , required = false)String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "id" , required = false)String sortDir



    ){

        List<PropertyUserDto> propertyUserEntitiesDto =
                pu.getPropertyUsers(pageSize,pageNo,sortBy,sortDir);

        return new ResponseEntity<>(propertyUserEntitiesDto, HttpStatus.OK);
    }

    //getmapping (Only one Id show )
    @GetMapping("/getUserById")
    public ResponseEntity<PropertyUserEntity> getPropertyUserById(
            @RequestParam long propertyUserId
    ){

        PropertyUserEntity propertyUserEntities =  pu.getPropertyUsersById(propertyUserId);

        return new ResponseEntity<>(propertyUserEntities, HttpStatus.OK);
    }


}
