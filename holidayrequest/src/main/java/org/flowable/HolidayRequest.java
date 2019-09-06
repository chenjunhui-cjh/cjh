package org.flowable;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class HolidayRequest
{
    public static void main( String[] args )
    {
        //流程引擎配置 运行 可建立数据库表
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/flowable_study?useSSL=false")
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //创建流程引擎
        ProcessEngine processEngine = cfg.buildProcessEngine();
        //部署流程定义到库中
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        //验证流程定义是否部署在引擎中
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());
        //启动流程实例 模拟数据来源api
        Scanner scanner= new Scanner(System.in);

        System.out.println("Who are you?");
//        String employee = scanner.nextLine();
        String employee = "张三";

        System.out.println("How many holidays do you want to request?");
//        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());
        Integer nrOfHolidays = 2;
        System.out.println("Why do you need them?");
//        String description = scanner.nextLine();
        String description = "dodo";
        //使用RuntimeService启动一个流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", employee);
        variables.put("nrOfHolidays", nrOfHolidays);
        variables.put("description", description);
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holidayRequest", variables);

        //获取流程中各个任务的列表
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("你有 " + tasks.size() + " 个任务:");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ") " + tasks.get(i).getName());
        }

        //根据id获取特定的任务
        System.out.println("你想执行哪个任务?");
        int taskIndex = Integer.valueOf(scanner.nextLine());
        Task task = tasks.get(taskIndex - 1);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " 想请 " +
                processVariables.get("nrOfHolidays") + " 天假期. 批准嘛?");

        //是否批准离开 当结果为y时 执行execute方法
        boolean approved = scanner.nextLine().toLowerCase().equals("y");
        variables = new HashMap<String, Object>();
        variables.put("approved", approved);
        taskService.complete(task.getId(), variables);


        //查看任务进程历史
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .finished()
                        .orderByHistoricActivityInstanceEndTime().asc()
                        .list();

        for (HistoricActivityInstance activity : activities) {
            System.out.println(activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }
    }
}
