/**
 * ClassName: ArraysStructure
 * Author:   Mr lei
 * Date:     2018/3/14 10:43
 * Description: 1. 将数组生成单链表
 * 2.打印链表
 * 3.获取链表的长度
 * 4. 向链表中插入数据
 * 5.删除某个位置的节点
 * 6.查找链表中倒数第k个节点
 * 7.查找单链表中的中间结点
 * 8.合并两个有序链表，合并之后的链表仍然有序
 * 9.单链表的反转
 * 10.从结尾出开始打印链表
 * 11.判断链表是否有环
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package linear_list;

import java.util.Stack;

public class ArraysStructure {
    /**
     * 将数组生成单链表
     * @param :num 数组
     */
    public static NodeList addList(int[] num){
        NodeList head=new NodeList(num[0]);
        NodeList current=head;
        for(int i=1;i<num.length;i++){
            NodeList next=new NodeList(num[i]);
            current.next=next;
            current=current.next;
        }
        return head;
    }

    /**
     * 打印链表
     */
    public static void printList(NodeList head){
        NodeList current=head;
        while(current!=null){
            System.out.print(current.data+"->");
            current=current.next;
        }
    }

    /**
     * 获取链表的长度
     */
    public static int getLength(NodeList head){
        //若链表为空
        if(head==null){
            return 0;
        }
        int length=0;
        NodeList current=head;
        while(current!=null){
            length++;
            current=current.next;
        }
        return length;
    }

    /**
     * 向链表中插入数据
     * @param :num要插入的内容
     * @param :k要插入的位置
     * @param ;head链表的头节点
     */
    public static NodeList insertNum(int num,int k,NodeList head){
        NodeList current=head;//当前指针指向头节点
        int i=0;
        while(current!=null&&i<k-1){
            current=current.next;
            i++;
        }
        if(current==null)return head;
        //插入数据
        NodeList next=new NodeList(num);
        next.next=current.next;//将插入的节点与后续节点连接起来
        current.next=next;
        current=current.next;
        return head;
    }

    /**
     * 删除某个位置的节点
     * @param: index要删除的节点位置
     */
    public static  NodeList deleteList(int index,NodeList head){
        if(head==null)return head;
        int i=0;
        NodeList current=head;
        while(current!=null&&i<index-1){
            current=current.next;
            i++;
        }
        if(index==0) {
            head=current.next;
        }else{
            current.next=current.next.next;
            current=current.next;
        }
        return head;
    }

    /**
     * 查找链表中倒数第k个节点
     * @param :index代表倒数第k个结点
     */
    public static  NodeList findLastNode(NodeList head,int index){
        if(index==0||head==null){
            return null;
        }
        NodeList first=head,second=head;
        for(int i=0;i<index-1;i++){
            second=second.next;
            if(second==null){
                return null;
            }
        }
        while(second!=null){
            first=first.next;
            second=second.next;
        }
        return first;
    }

    /**
     * 查找单链表中的中间结点：方法与查找倒数第k个节点一样，只不过second每次走两步，first每次走一步
     */
    public static  NodeList findMidNode(NodeList head){
        if(head==null)return null;
        NodeList first=head,second=head;
        while(second!=null&&second.next!=null){
            first=first.next;
            second=second.next.next;
        }
        return first;
    }

    /**
     * 合并两个有序链表，合并之后的链表仍然有序
     */
    public static  NodeList mergeList(NodeList head1,NodeList head2){
        if(head1==null&&head2==null)return null;
        if(head1==null)return head2;
        if(head2==null)return head1;
        NodeList head;//新链表的头结点
        NodeList current;//指向新链表
        if(head1.data<head2.data){
            head=head1;
            current=head1;
            head1=head1.next;
        }else{
            head=head2;
            current=head2;
            head2=head2.next;
        }
        while(head1!=null&&head2!=null){
            if(head1.data<head2.data){
                current.next=head1;
                current=current.next;
                head1= head1.next;
            }else{
                current.next=head2;
                current=current.next;
                head2= head2.next;
            }
        }
        //合并剩余的元素
        if(head1!=null){
            current.next=head1;
        }
        if(head2!=null) {
            current.next = head2;
        }
        return head;
    }

    /**
     * 单链表的反转
     */
    public static NodeList reverseList(NodeList head){
        //如果链表为空或者只有一个节点，无需反转，直接返回
        if(head==null||head.next==null){
            return  head;
        }
        NodeList current=head;
        NodeList next=null;
        NodeList reHead=null;
        while(current!=null){
            next=current.next;
            current.next=reHead;
            reHead=current;
            current=current.next;
        }
        return  reHead;
    }

	public static Node reverseList(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Node reHead = null;// 定义新链表头结点
    while (head != null) {
      Node cur = head.next;// 记录下一个节点
      head.next = reHead;// 将rehead节点连接到head节点上
      reHead = head;// 让rehead指向head
      head = cur;// 将head指向下一个节点
    }
    return reHead;
  }
  
  
  /**
   * 递归反转链表
   * 
   * @param head
   * @return
   */
  public static Node reverseList2(Node head) {
    if (head == null || head.next == null)
      return head;
    Node rehead = reverseList2(head.next);
    head.next.next = head;// 将头节点置于末端
    head.next = null;// 防止链表循环
    return rehead;
  }
  
    /**
     * 从结尾出开始打印链表
     */
    public static void printListFromTail(NodeList head){
        if(head==null)return;
        Stack<NodeList> stack=new Stack<>();
        NodeList current=head;
        while(current!=null){
            stack.push(current);
            current=current.next;
        }
        //将栈中数据链表输出
        while(stack.size()>0){
            System.out.print(stack.pop().data);
        }

    }

    /**
     * 判断单链表是否有环:用两个指针，first每次走一次，second每次走两步，这样若两指针相遇说明有环
     */
    public static boolean hasCycle(NodeList head){
        if(head==null)return false;
        NodeList first=head,second=head;
        while(second!=null){
            first= first.next;
            second= second.next.next;
            if(first==second){
                return  true;
            }
        }
        return  false;
    }


    public static void main(String[] args){
        int [] num={4,1,5,7,8};
        printList(addList(num));
        //获取链表长度
        System.out.println("链表长度："+getLength(addList(num)));
        //插入数据
        printList(insertNum(10,5,addList(num)));
        //删除节点
        System.out.println();
        printList(deleteList(4,addList(num)));
    }

}

/**
 * 作为链表
 * @param :next存储下一节点存储地址
 * @param :data存储数据
 */
class  NodeList{
    NodeList next=null;
    int data;
    public NodeList(int data){
        this.data=data;
    }
        }