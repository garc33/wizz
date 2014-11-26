package fr.herman.wizz.demo;

import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import fr.herman.wizz.bytes.FileChannelByteEncoder;
import fr.herman.wizz.bytes.serializer.StringUTF8Serializer;
import fr.herman.wizz.mapping.ObjectMapping;
import fr.herman.wizz.mapping.ObjectMappingBuilder;
import fr.herman.wizz.serializer.enums.EnumNameSerializer;
import fr.herman.wizz.serializer.numbers.IntegerSerializer;

public class ByteBufferDemo
{
    public static enum Gender
    {
        MALE, FEMALE;
    }

    public static class Person
    {
        private String firstName, lastName;
        private Integer age;
        private Gender gender;

        public String getFirstName()
        {
            return firstName;
        }

        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }

        public String getLastName()
        {
            return lastName;
        }

        public void setLastName(String lastName)
        {
            this.lastName = lastName;
        }

        public Integer getAge()
        {
            return age;
        }

        public void setAge(Integer age)
        {
            this.age = age;
        }

        public Gender getGender()
        {
            return gender;
        }

        public void setGender(Gender gender)
        {
            this.gender = gender;
        }
    }

    public static void main(String... strings) throws Exception
    {
        ObjectMapping<Person> mapping = new ObjectMappingBuilder<>(Person::new)
                .map(Person::setFirstName, Person::getFirstName, new StringUTF8Serializer())
                .map(Person::setLastName, Person::getLastName, new StringUTF8Serializer())
            .map(Person::setAge, Person::getAge, new IntegerSerializer())
            .map(Person::setGender, Person::getGender, new EnumNameSerializer<>(Gender.class))
            .build();
        // FileWriter fw = new FileWriter("c:\\testCsv.txt");

        Path path = Paths.get("C:/testByte.txt");
        Files.deleteIfExists(path);
        FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);
        FileChannelByteEncoder encoder = new FileChannelByteEncoder(channel, 8192);
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAge(Integer.valueOf("31"));
        person.setGender(Gender.MALE);
        Person person2 = new Person();
        person2.setFirstName("Jâne");
        person2.setLastName("Doe");
        person2.setAge(Integer.valueOf("27"));
        person2.setGender(Gender.FEMALE);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++)
        {
            mapping.write(encoder, i % 2 == 0 ? person : person2);
        }
        encoder.flush();
        channel.close();
        System.out.println(System.currentTimeMillis() - time);
    }
}
