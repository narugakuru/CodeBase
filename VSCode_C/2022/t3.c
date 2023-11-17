#include <stdio.h>
#include <stdlib.h>

// 定义链表节点结构体
typedef struct Node
{
    int data;
    struct Node *next;
} Node;

// 创建新节点
Node *createNode(int data)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

// 合并两个有序链表
Node *mergeLists(Node *list1, Node *list2)
{
    // 创建一个新链表的头结点
    Node *mergedList = (Node *)malloc(sizeof(Node));
    Node *current = mergedList;

    // 比较两个链表的节点值，将较小的节点添加到新链表中
    while (list1 != NULL && list2 != NULL)
    {
        if (list1->data <= list2->data)
        {
            current->next = list1;
            list1 = list1->next;
        }
        else
        {
            current->next = list2;
            list2 = list2->next;
        }
        current = current->next;
    }

    // 将剩余的节点添加到新链表中
    if (list1 != NULL)
    {
        current->next = list1;
    }
    else
    {
        current->next = list2;
    }

    // 返回合并后的链表头结点
    return mergedList;
}

// 打印链表
void printList(Node *head)
{
    Node *current = head->next;
    while (current != NULL)
    {
        printf("%d ", current->data);
        current = current->next;
    }
    printf("\n");
}

int main()
{
    // 创建第一个有序链表：1->3->5->NULL
    Node *list1 = createNode(1);
    list1->next = createNode(3);
    list1->next->next = createNode(5);

    // 创建第二个有序链表：2->4->6->NULL
    Node *list2 = createNode(2);
    list2->next = createNode(4);
    list2->next->next = createNode(6);

    printf("List 1: ");
    printList(list1);

    printf("List 2: ");
    printList(list2);

    // 合并两个有序链表
    Node *mergedList = mergeLists(list1, list2);

    printf("Merged List: ");
    printList(mergedList);

    // 释放链表节点的内存
    Node *temp;
    while (mergedList != NULL)
    {
        temp = mergedList;
        mergedList = mergedList->next;
        free(temp);
    }
    system("pause");
    return 0;
}
