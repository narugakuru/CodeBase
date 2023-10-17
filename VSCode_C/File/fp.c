#include <stdio.h>
void Readf();

void Readf()
{
   FILE *fp = NULL;
   char buff[255];

   fp = fopen("D:/Code/VSCode_C/File/ansi.txt", "r");
   //    把fp读取的值给buff，fp读取到空格停止
   fscanf(fp, "%s", buff);
   printf("1: %s\n", buff);

   // 把buff的值写入到fp文件
   fprintf(fp, "%s", buff);

   //  把fp读取的值给buff，fp读取到换行停止
   fgets(buff, 255, (FILE *)fp);
   printf("2: %s\n", buff);

   fgets(buff, 255, (FILE *)fp);
   printf("3: %s\n", buff);
   // 关闭文件，这个函数实际上，会清空缓冲区中的数据，关闭文件，并释放用于该文件的所有内存。
   fclose(fp);
}

void Writef()
{
   FILE *fp = NULL;
   char s[50];
   fp = fopen("D:/Code/VSCode_C/File/ansi.txt", "w+");
   // fprintf(fp, "This is testing for fprintf...\n");

   printf("INPUT:\n");
   //输入至s，再由s转入文本
   gets(s);
   fputs(s, fp);

   fclose(fp);
}

/* 
重点！！是s不是c！！
fputs(char, fp); 将字符串char[]写入文件
fgets(char, int , (FILE *)fp); 读取文件至字符串char 
 */

int main()
{
   Writef();
   printf("再见\0");
//    system("pause");
   return 0;
}