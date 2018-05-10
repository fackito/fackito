# fackito
Fakes your Mockito POJOs for unit tests written in Java.

Pre-populates your POJOs with data like names, emails, dates, countries...

## Usage

Given a `com.fackito.User` POJO class:
        
    package com.fackito;
    public class User {
        private String name;
        private String phoneNumber;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }       
        public String getPhoneNumber() {
            return phoneNumber;
        }
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

Create a YAML file: `com.fackito.User.yaml` (`{package-name}.{class-name}.yaml`)

    default:
      name: ${name.name}
      phoneNumber: ${phoneNumber.phoneNumber}
    myName:
      name: My Name
      phoneNumber: ${phoneNumber.phoneNumber}

Now that everything is setup, test!
    
    // Fake POJO with 'default'
    User user = fake(User.class);
    
    // the following prints a random name
    System.out.println(user.getName());
    
    // Fake POJO with 'myName'
    User myNameUser = fake(User.class, "myName");
    
    // the following prints "My Name" as specified in the YAML file under 'myName'
    System.out.println(myNameUser.getName());

    // can still use Mockito to mock
    // stub it
    when(userRepository.save(user)).thenReturn(user);
    
    // the following verification passes because
    // .save(user) has been invoked
    verify(userRepository).save(user);
    
    // the following verification fails because
    // .save(myNameUser) has not been invoked
    verify(userRepository).save(myNameUser);