package Class;

import java.util.Arrays;

public class PersonTest {

	public static class Person{

		private  String name;
        private  String sex;
		private  int age;
		//private Person[] child=null;
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    '}';
        }

        public void print() {//输出

			System.out.println("name "+"sex "+"age ");
			System.out.print(this.name+"   ");
			System.out.print(this.sex+"   ");
			System.out.println(this.age);

		}
	}
	public static void main(String args[]) {
        Person p1 = new Person();
    }
}
