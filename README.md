# fackito
Stubs Java objects with Fake data using the [Mockito framework](https://github.com/mockito/mockito) and 
[Faker](https://github.com/blocoio/faker) (A Java port of the Faker ruby gem: [faker](https://github.com/stympy/faker))
## Why?
Fackito's makes testing easier and better! by
1. increasing test development speed and
2. hardening of tests

Fackito achieves the above by
1. externalizing data from the actual test definition
2. allowing the re-use of defined test data
3. randomizing all or parts of the data set
## But Why?
Setting up data for unit tests is a **tedious** task. You may need to create different variations of the same object to 
test different flows. You may find the following piece of code all over your code:

    User user1 = mock(User.class);
    when(user1.getFirstName()).thenReturn(...);
    when(user1.getLastName()).thenReturn(...);
    ...
    User user2 = mock(User.class);
    when(user2.getFirstName()).thenReturn(...);
    when(user2.getLastName()).thenReturn(...);

In addition to that madness, developers tend to copy and paste test chunks, therefore, after a while, you end up with a bunch 
of similar mock objects all over your tests!

Fackito solves this by pre-stubbing your mocks by using definition files.

    User user = fake(User.class)
    
All the data found in your definitions would be used to `fake` your mock so that you don't have to spend time stubbing it.

In addition to that, with the fact that the definition is externalized, you can now reuse it all over your tests.

    User user = fake(User.class, "oldTimer");

    User user = fake(User.class, "youngster");

Since the definitions for `"oldTimer"` and  `"youngster"` are externalized, they can be reused all over your tests 
with no extra code!

**Also**, Fackito allows you to randomize your test data. 

    // provided your definition is set to randomize the "firstName"
    User user = fake(User.class);
    user.getFirstName(); // returns a different value per test run

Every time your test runs, the `user.getFirstName()` will return different data. In some cases, this randomization makes 
sense but at the end, it is up to you to make that decision. Fackito can `fake` data, such as names, emails, dates, 
countries, etc.

---

## How it works

Fackito uses Mockito's stubbing functionality to create a `fake` object.

When a `fake` is requested, Fackito looks up the `fake` definition for the class being requested. If the definition
is found, Fackito uses the data to stub a Mockito object.

Fackito uses `YAML` to define a `fake` object. The file's name convention is `{package}.{class}.yaml}` and placed within
the test resources.

    src
     L test
        L resources
           L com.fackito.User.yaml # note package + class name + yaml extension

The `YAML` file's content (definition) is defined as follows:
    
    {fake-identifier}: # if need only one, then identifier must be 'default'
        {attribute-name}: {attribute value or faker}
    
    
    # Example
    ---
    default:
        name: My Name # this fake definition is  preset to "My Name"
    anotherUser: # another example within the same definition
        name: ${name.name}
    
    
    The class will have to have a getName() method for the above definition to be stubbed.

Fackito uses [Faker](https://github.com/blocoio/faker) (A Java port of the Faker ruby gem: [faker](https://github.com/stympy/faker)) 
to `fake` data, therefore all of its functions can be used. 

For instance, `Faker.Name` has the following properties:

    name
    firstName
    lastName
    nameWithMiddle
    prefix
    title

The above can be used as follows:

    # Example
    ---
    default:
        prefix: ${name.prefix}
        firstName: ${name.firstName}
        lastName: ${name.lastName}
        title: ${name.title}

Fackito will match attributes found in the definition with the class' getters and stub them as follows:

    F fake = mock(fake); // fake is the class being faked - uses Mockito!
    Object fakeValue; // from the definition, either uses a preset value or resolves to the fake data
    // for all found getters, stub the mock
    when(fakeGetMethod).thenReturn(definitionValue);

All the above is triggered just by calling fake:

    User user = fake(User.class); // uses the "default" identifier
    User anotherUser = fake(User.class, "anotherUser); // uses the "anotherUser" identifier

## Usage

Given a `com.fackito.User` class:
        
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

Create a `YAML` file: `com.fackito.User.yaml`

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

## Contact

Feedback and contributions are welcome.
Feel free to send an [email](mailto:jccarrillo@acm.org) or submit a pull request.

## License

This code is free to use under the terms of the [MIT license](https://github.com/fackito/fackito/blob/master/LICENSE).