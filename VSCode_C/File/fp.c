#include <stdio.h>
void Readf();

void Readf()
{
   FILE *fp = NULL;
   char buff[255];

   fp = fopen("D:/Code/VSCode_C/File/ansi.txt", "r");
   //    ��fp��ȡ��ֵ��buff��fp��ȡ���ո�ֹͣ
   fscanf(fp, "%s", buff);
   printf("1: %s\n", buff);

   // ��buff��ֵд�뵽fp�ļ�
   fprintf(fp, "%s", buff);

   //  ��fp��ȡ��ֵ��buff��fp��ȡ������ֹͣ
   fgets(buff, 255, (FILE *)fp);
   printf("2: %s\n", buff);

   fgets(buff, 255, (FILE *)fp);
   printf("3: %s\n", buff);
   // �ر��ļ����������ʵ���ϣ�����ջ������е����ݣ��ر��ļ������ͷ����ڸ��ļ��������ڴ档
   fclose(fp);
}

void Writef()
{
   FILE *fp = NULL;
   char s[50];
   fp = fopen("D:/Code/VSCode_C/File/ansi.txt", "w+");
   // fprintf(fp, "This is testing for fprintf...\n");

   printf("INPUT:\n");
   //������s������sת���ı�
   gets(s);
   fputs(s, fp);

   fclose(fp);
}

/* 
�ص㣡����s����c����
fputs(char, fp); ���ַ���char[]д���ļ�
fgets(char, int , (FILE *)fp); ��ȡ�ļ����ַ���char 
 */

int main()
{
   Writef();
   printf("�ټ�\0");
//    system("pause");
   return 0;
}