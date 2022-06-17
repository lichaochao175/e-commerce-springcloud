package com.imooc.ecommerce.service.async;

import com.imooc.ecommerce.constant.AsyncTaskStatusEnum;
import com.imooc.ecommerce.goods.GoodsInfo;
import com.imooc.ecommerce.vo.AsyncTaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author lichaochao
 * @data 2022/6/17 8:52 AM
 * t异步任务执行管理器
 * 对异步任务进行包装管理，记录塞入异步任务信息
 **/
@Slf4j
@Component
public class AsyncTaskManager {

    /**
     * 异步任务执行信息容器
     */
    private final Map<String, AsyncTaskInfo> taskContainer = new HashMap<>(16);

    private final IAsyncService asyncService;

    private AsyncTaskManager(IAsyncService asyncService) {
        this.asyncService = asyncService;
    }

    /**
     * 初始化异步任务
     */
    public AsyncTaskInfo initTask() {
        AsyncTaskInfo taskInfo = new AsyncTaskInfo();
        // 设置唯一的异步任务id
        taskInfo.setTaskId(UUID.randomUUID().toString());
        taskInfo.setStatus(AsyncTaskStatusEnum.STARTED);
        taskInfo.setStartTime(new Date());

        //初始化的时候将异步任务执行信息放入到存储容器中
        taskContainer.put(taskInfo.getTaskId(), taskInfo);
        return taskInfo;
    }

    /**
     * 提交异步任务
     */
    public AsyncTaskInfo submit(List<GoodsInfo> goodsInfos) {

        //初始化一个异步任务的监控信息
        AsyncTaskInfo taskInfo = initTask();
        asyncService.asyncImportGoods(goodsInfos, taskInfo.getTaskId());
        return taskInfo;
    }

    /*** 设置异步任务执行状态信息*/
    public void setTaskInfo(AsyncTaskInfo taskInfo) {
        taskContainer.put(taskInfo.getTaskId(), taskInfo);
    }

    /***获取异步任务执行状态信息*/
    public AsyncTaskInfo getTaskInfo(String taskId) {
        return taskContainer.get(taskId);
    }
}
