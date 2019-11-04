package com.cai;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Generator {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String d = format.format(date.getTime()+3600*1000);
        Date dates =format.parse(d);
        System.out.println("Format To String(Date):"+d);
        System.out.println("Format To Date:"+format.format(dates));

        System.out.println(2*12);
    }
       /* List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //如果这里出现空指针，直接写绝对路径即可。
        String genCfg = "D:\\CBQ\\cbqwork\\java\\20190731springbootsecurity\\server-project-user\\src\\main\\resources\\generatorConfig.xml";
//        File configFile = new File(GenMain.class.getResource(genCfg).getFile()); //获取路径出错
        File configFile = ResourceUtils.getFile(genCfg);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            *//*alter table course alter column status drop default 0;
            alter table course alter column coursetype drop default 1;*//*
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    /*public static void main(String[] args){
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        String genCfg = "D:\\CBQ\\cbqwork\\java\\20190731springbootsecurity\\server-project-user\\src\\main\\resource\\generatorConfig.xml";
        File configFile = new File(Generator.class.getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (org.mybatis.generator.exception.InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

}
