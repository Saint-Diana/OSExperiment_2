import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 模拟磁盘调度
 *  提供三种磁盘调度算法：
 *  1、先来先服务算法（FCFS）
 *  2、最短寻道时间优先算法（SSTF）
 *  3、扫描算法/电梯算法（SCAN）
 *
 * @author Shen
 * @date 2022/12/15 16:29
 */
public class Disk {
    /**
     * 磁盘含有的磁道数
     */
    private final int diskPathNum;

    public Disk(int diskPathNum) {
        this.diskPathNum = diskPathNum;
    }

    /**
     * 先来先服务算法
     *      按照访问磁盘请求的顺序进行磁盘调度
     *
     * @param diskHeadPos 磁头初始位置
     * @param request 磁盘访问请求序列
     * @param process 记录走道顺序
     * @return 磁头走过的总道数
     */
    public int FCFS(int diskHeadPos, ArrayList<Integer> request, ArrayList<Integer> process) {
        int ret = 0;
        if(!request.contains(diskHeadPos))
            process.add(diskHeadPos);
        //按请求顺序进行处理，计算磁头走过的总磁道数，并且记录走道顺序
        for (Integer integer : request) {
            ret += Math.abs(integer - diskHeadPos);
            process.add(integer);
            diskHeadPos = integer;
        }
        return ret;
    }


    /**
     * 最短寻道时间优先算法：
     *      优先选择距当前磁头最近的访问请求进行服务
     *
     * @param diskHeadPos 磁头初始位置
     * @param request 磁盘访问请求序列
     * @param process 记录走道顺序
     * @return 磁头走过的总道数
     */
    public int SSTF(int diskHeadPos, ArrayList<Integer> request, ArrayList<Integer> process) {
        int ret = 0;
        if(!request.contains(diskHeadPos))
            process.add(diskHeadPos);
        while (!request.isEmpty()){
            int min = diskPathNum + 5;
            int flag = -1;
            //遍历request找到距离当前磁头位置最近的访问请求
            for(int i = 0;i < request.size();++i){
                if(Math.abs(request.get(i) - diskHeadPos) < min){
                    min = Math.abs(request.get(i) - diskHeadPos);
                    flag = i;
                }
            }
            ret += Math.abs(request.get(flag) - diskHeadPos);
            process.add(request.get(flag));
            diskHeadPos = request.get(flag);
            request.remove(flag);
        }
        return ret;
    }

    public int SCAN(int diskHeadPos, ArrayList<Integer> request, int direction, ArrayList<Integer> process){
        int ret = 0;
        process.add(diskHeadPos);
        if(request.contains(diskHeadPos))
            request.remove((Integer) diskHeadPos);
        //首先将request按照值从小到大的升序进行排列
        request.sort(Comparator.naturalOrder());
        //然后进行移动
        if(diskHeadPos <= request.get(0)) {
            ret += request.get(request.size() - 1) - diskHeadPos;
            process.addAll(request);
        } else if(diskHeadPos >= request.get(request.size() - 1)) {
            ret += diskHeadPos - request.get(0);
            Collections.reverse(request);
            process.addAll(request);
        } else {
            //找到第一个大于等于磁头初始位置的请求
            int flag = -1;
            for(int i = 0;i < request.size();++i){
                if(request.get(i) >= diskHeadPos){
                    flag = i;
                    break;
                }
            }
           if(direction == 1) {
               ret += request.get(request.size() - 1) - diskHeadPos;
               for(int i = flag;i < request.size();++i){
                   process.add(request.get(i));
               }
               for(int i = flag - 1;i >= 0;--i){
                   process.add(request.get(i));
               }
           } else {
               ret += diskHeadPos - request.get(0);
               for(int i = flag - 1;i >= 0;--i){
                   process.add(request.get(i));
               }
               for(int i = flag;i < request.size();++i){
                   process.add(request.get(i));
               }
           }
           ret += request.get(request.size() - 1) - request.get(0);
        }
        return ret;
    }
}
