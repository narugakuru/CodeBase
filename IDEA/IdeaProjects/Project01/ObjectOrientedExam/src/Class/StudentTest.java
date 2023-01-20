package Class;

public class StudentTest {
    public static class Student{
    		 private int no;
    		 private String name;
    		 private int age;

    		@Override
    		public String toString() {
    			return "StudentTest [no=" + no + ", name=" + name + ", age=" + age + "]";
    		}
    		/*public void input(){//输入
    		 		this.no=1;
    		 		this.name="嘤嘤嘤";
    		 		this.age=24
    		}*/
    		public  Student(int no, String name, int age) {
    			super();
    			this.no = no;
    			this.name = name;
    			this.age = age;
    		}

    		public void print() {//输出

    			System.out.println("No "+"name "+"age ");
    			System.out.print(this.no+"   ");
    			System.out.print(this.name+"   ");
    			System.out.println(this.age);

    		}
    	}
    	public static void main(String args[]) {
    			Student[] s= new Student[6];
    			//自动赋值
    			String[] arr= {"0","a","b","c","d","e"};
    			for(int i=1;i<=5;i++) {
    				s[i] =new Student(i,arr[i],i*10);
    				s[i].print();
    			}
    			//s[0] =new Student(1,"a",30);
    		}
}
