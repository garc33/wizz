wizz
====
Java serializer/deserializer layout

Supported formats:
* Simple CSV : No quote, no escape, no CRLF at this time
* Bytes 

How to use it:
```java
ObjectMapping<Person> mapping = new ObjectMappingBuilder<>(Person::new)
    .map(Person::setFirstName, Person::getFirstName, new StringSerializer())
    .map(Person::setLastName, Person::getLastName, new StringSerializer())
    .map(Person::setAge, Person::getAge, new IntegerSerializer())
    .map(Person::setGender, Person::getGender, new EnumNameSerializer<>(Gender.class))
    .build();
            
FileWriter fw = new FileWriter("testCsv.txt");
CsvEncoder encoder = new CsvEncoder(fw, ',', '\n');

Person person = new Person();
person.setFirstName("John");
person.setLastName("Doe");
person.setAge(Integer.valueOf("31"));
person.setGender(Gender.MALE);
Person person2 = new Person();
person2.setFirstName("Jane");
person2.setLastName("Doe");
person2.setAge(Integer.valueOf("27"));
person2.setGender(Gender.FEMALE);

long time = System.currentTimeMillis();
for (int i = 0; i < 10_000_000; i++)
{
    mapping.write(encoder, i % 2 == 0 ? person : person2);
}
encoder.flush();
fw.close();
System.out.println(System.currentTimeMillis() - time);
```
10M lines per second, that's all.
