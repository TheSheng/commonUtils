package com.lisheng.project.code;



import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;

/**
 * @ClassName： GenerateCode
 * @description: 生成适用于tro项目的controller和service
 * @author: 李胜
 * @create: 2020-06-08 15:12
 **/
public class GenerateCode {
    static class GenerateEntity{
        //mapper全路径类名
        private String mapper;
        //entity全路径类名
        private String entity;
        //生成的service名字
        private String serviceName;
        //mapper仅类名
        private String mapperName;
        //mapper对应的参数名
        private String mapperFiledName;
        //entity仅类名
        private String entityName;
        //package末尾
        private String packageName;

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getMapper() {
            return mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getMapperName() {
            return mapperName;
        }

        public void setMapperName(String mapperName) {
            this.mapperName = mapperName;
        }

        public String getMapperFiledName() {
            return mapperFiledName;
        }

        public void setMapperFiledName(String mapperFiledName) {
            this.mapperFiledName = mapperFiledName;
        }

        public String getEntityName() {
            return entityName;
        }

        public void setEntityName(String entityName) {
            this.entityName = entityName;
        }
    }
    static  class GenerateController{
        private String entity;
        private String service;
        private String packageName;
        private String controller;
        private String serviceName;
        private String path;
        private String entityName;

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getController() {
            return controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getEntityName() {
            return entityName;
        }

        public void setEntityName(String entityName) {
            this.entityName = entityName;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        //generateService(TSourceVersion.class, TSourceVersionMapper.class,"version");
//        generateController(TSourceVersion.class, TSourceVersionService.class,"version");
    }
    public static final String FILE_PATH="src/main/java/";
    public static  void generateController(Class entity,Class service,String packageName){
        GenerateController generateEntity=new GenerateController();
        setControllerParam(entity,service,generateEntity);
        generateEntity.setPackageName("com.deppon.tro.web.api.controller."+packageName);
        generateEntity.setPath(packageName);
        BufferedReader br = null;
        try {
            File file=new File(entity.getClassLoader().getResource("controller.template").toURI());
            br=new BufferedReader(new FileReader(file));
            StringBuilder template=new StringBuilder();
            br.lines().forEach(line->template.append(line).append("\n"));
            String writeDocumtes=generateFinalController(template.toString(),generateEntity);
            System.out.println(writeDocumtes);
            //获取正文目录
            String absolutePath = file.getAbsolutePath();
            absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("target"));

            String reallyPath =absolutePath+ FILE_PATH+generateEntity.getPackageName().replaceAll("\\.", "/");
            System.out.println(reallyPath);
            File servieDir=new File(reallyPath);
            if(!servieDir.exists()){
                Files.createDirectory(servieDir.toPath());
            }else{
                Files.delete(servieDir.toPath());
            }
            File controller = new File(reallyPath + "/" + generateEntity.getController()+".java");
            System.out.println(controller.getAbsolutePath());
            if(controller.exists()){
                controller.delete();
            }else{
                controller.createNewFile();
            }
            BufferedWriter bw=new BufferedWriter(new FileWriter(controller));
            bw.write(writeDocumtes);
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                br.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void generateService(Class entity,Class mapper,String packageName) throws FileNotFoundException {
        GenerateEntity generateEntity=new GenerateEntity();
        setParam(entity,mapper,generateEntity);
        generateEntity.setPackageName("com.deppon.tro.web.api.service."+packageName);
        BufferedReader br = null;
        try {
            File file=new File(entity.getClassLoader().getResource("service.template").toURI());
             br=new BufferedReader(new FileReader(file));
            StringBuilder template=new StringBuilder();
            br.lines().forEach(line->template.append(line).append("\n"));
            String writeDocumtes=generateFinalService(template.toString(),generateEntity);
            //获取正文目录
            String absolutePath = file.getAbsolutePath();
            absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("target"));

            String reallyPath =absolutePath+ FILE_PATH+generateEntity.getPackageName().replaceAll("\\.", "/");
            System.out.println(reallyPath);
            File servieDir=new File(reallyPath);
            if(!servieDir.exists()){
                Files.createDirectory(servieDir.toPath());
            }
            File service = new File(reallyPath + "/" + generateEntity.getServiceName()+".java");
            System.out.println(service.getAbsolutePath());
            if(service.exists()){
                service.delete();
            }else{
                service.createNewFile();
            }
            BufferedWriter bw=new BufferedWriter(new FileWriter(service));
            bw.write(writeDocumtes);
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                br.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static String generateFinalService(String service, GenerateEntity generateEntity) throws Exception{
        Field[] declaredFields = generateEntity.getClass().getDeclaredFields();
        for(Field field:declaredFields){
            field.setAccessible(true);
            Object o = field.get(generateEntity);
            String name = field.getName();
            service = service.replaceAll(String.format("\\#\\{%s\\}", name), o.toString());
        }
        return service;
    }
    public static String generateFinalController(String service, GenerateController generateEntity) throws Exception{
        Field[] declaredFields = generateEntity.getClass().getDeclaredFields();
        for(Field field:declaredFields){
            field.setAccessible(true);
            Object o = field.get(generateEntity);
            String name = field.getName();
            service = service.replaceAll(String.format("\\#\\{%s\\}", name), o.toString());
        }
        return service;
    }
    public static void setParam(Class entity,Class mapper,GenerateEntity generateEntity){
        generateEntity.setMapper(mapper.getName());
        generateEntity.setEntity(entity.getName());
        String simpleName = mapper.getSimpleName();
        generateEntity.setMapperName(simpleName);
        String mapperFiledName=(char)(simpleName.charAt(0)+32)+simpleName.substring(1,simpleName.length());
        generateEntity.setMapperFiledName(mapperFiledName);
        String simpleEntityName=entity.getSimpleName();
        generateEntity.setEntityName(simpleEntityName);
        generateEntity.setServiceName(simpleEntityName+"Service");
    }
    public static void setControllerParam(Class entity,Class service,GenerateController generateEntity){
        generateEntity.setService(service.getName());
        generateEntity.setEntity(entity.getName());
        String simpleName = service.getSimpleName();
        generateEntity.setServiceName(simpleName);
        String simpleEntityName=entity.getSimpleName();
        generateEntity.setEntityName(simpleEntityName);
        generateEntity.setController(simpleEntityName+"Controller");

    }
}
