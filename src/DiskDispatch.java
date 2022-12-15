import java.util.ArrayList;
import java.util.Scanner;

/**
 * 磁盘调度类
 * 测试数据：200 1 53      98 183 37 122 14 124 65 67
 *
 * @author Shen
 * @date 2022/12/15 16:44
 */
public class DiskDispatch {
    public static void main(String[] args) {
        System.out.println("---------------磁盘调度程序---------------");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入磁盘磁道数：");
        int diskPathNum = sc.nextInt();
        Disk disk = new Disk(diskPathNum);
        while (true){
            System.out.println("请选择以下磁盘调度算法：");
            System.out.println("1、先来先服务算法");
            System.out.println("2、最短寻道时间优先算法");
            System.out.println("3、电梯算法");
            System.out.println("4、退出程序");
            System.out.print("请输入您的选择：");
            int choice = sc.nextInt();
            switch (choice){
                case 1: {
                    System.out.print("请输入磁头初始位置：");
                    int diskHeadPos = sc.nextInt();
                    ArrayList<Integer> request = new ArrayList<>();
                    ArrayList<Integer> process = new ArrayList<>();
                    init(request);
                    int result = disk.FCFS(diskHeadPos, request, process);
                    if(result != -1){
                        System.out.println("磁头移动的总磁道数：" + result);
                        System.out.print("走道顺序：");
                        for(Integer i : process){
                            System.out.print(i + " ");
                        }
                        System.out.println();
                        System.out.println();
                    }
                    break;
                }
                case 2: {
                    System.out.print("请输入磁头初始位置：");
                    int diskHeadPos = sc.nextInt();
                    ArrayList<Integer> request = new ArrayList<>();
                    ArrayList<Integer> process = new ArrayList<>();
                    init(request);
                    int result = disk.SSTF(diskHeadPos, request, process);
                    if(result != -1){
                        System.out.println("磁头移动的总磁道数：" + result);
                        System.out.print("走道顺序：");
                        for(Integer i : process){
                            System.out.print(i + " ");
                        }
                        System.out.println();
                        System.out.println();
                    }
                    break;
                }
                case 3: {
                    System.out.print("请输入磁头初始位置：");
                    int diskHeadPos = sc.nextInt();
                    ArrayList<Integer> request = new ArrayList<>();
                    ArrayList<Integer> process = new ArrayList<>();
                    init(request);
                    System.out.print("请输入磁头移动方向：");
                    int direction = sc.nextInt();
                    int result = disk.SCAN(diskHeadPos, request, direction, process);
                    if(result != -1){
                        System.out.println("磁头移动的总磁道数：" + result);
                        System.out.print("走道顺序：");
                        for(Integer i : process){
                            System.out.print(i + " ");
                        }
                        System.out.println();
                        System.out.println();
                    }
                    break;
                }
                case 4: {
                    System.exit(0);
                }
                default: {
                    System.out.println("输入错误，请重新输入！");
                }
            }
        }
    }

    private static void init(ArrayList<Integer> request) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入磁盘访问序列：");
        String line = sc.nextLine();
        Scanner in = new Scanner(line);
        while (in.hasNextLine()) {
            request.add(in.nextInt());
        }
    }
}
