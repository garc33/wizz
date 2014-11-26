package fr.herman.wizz.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import fr.herman.wizz.mapping.ObjectMapping;
import fr.herman.wizz.mapping.ObjectMappingBuilder;
import fr.herman.wizz.serializer.enums.EnumNameSerializer;
import fr.herman.wizz.serializer.numbers.IntegerSerializer;
import fr.herman.wizz.serializer.strings.StringSerializer;
import fr.herman.wizz.string.csv.CsvEncoder;

public class CsvDemo
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

    public static class NullOutputStream extends OutputStream {
        @Override
        public void write(int b) {
        }

        @Override
        public void write(byte[] b) throws IOException {
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
        }

        @Override
        public void flush() throws IOException {
        }

        @Override
        public void close() throws IOException {
        }

    }

    public static void main(String... strings) throws Exception
    {
        ObjectMapping<Person> mapping = new ObjectMappingBuilder<>(Person::new)
            .map(Person::setFirstName, Person::getFirstName, new StringSerializer())
            .map(Person::setLastName, Person::getLastName, new StringSerializer())
            .map(Person::setAge, Person::getAge, new IntegerSerializer())
            .map(Person::setGender, Person::getGender, new EnumNameSerializer<>(Gender.class))
            .build();
        // FileWriter fw = new FileWriter("c:\\testCsv.txt");
        Writer fw = new OutputStreamWriter(new NullOutputStream());
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
    }
}
