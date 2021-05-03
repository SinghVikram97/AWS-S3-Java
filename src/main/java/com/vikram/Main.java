package com.vikram;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.xspec.B;
import com.amazonaws.services.lambda.model.Environment;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Person{
    String name;
    int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        String bucketName="vbedi-test-demo-ingestion-data-bucket";

        /* Creating a bucket

        if(!s3Client.doesBucketExist(bucketName)){
            s3Client.createBucket(bucketName);
        }

        */

        /* Deleting a bucket

        try{
            s3Client.deleteBucket(bucketName);
        }catch (AmazonServiceException e){
            System.out.println(e.getErrorMessage());
        }

*/
        /* Adding tags to a bucket-> https://stackoverflow.com/questions/57918870/aws-s3-bucket-tagging

        s3Client.createBucket(bucketName);

        BucketTaggingConfiguration bucketTaggingConfiguration= s3Client.getBucketTaggingConfiguration(bucketName);

        // TODO: Add not null case
        if(bucketTaggingConfiguration==null){
            TagSet tagSet=new TagSet();
            tagSet.setTag("CustomKey","CustomValue");

            List<TagSet> tagSetList=new ArrayList<>();

            tagSetList.add(tagSet);

            bucketTaggingConfiguration=new BucketTaggingConfiguration();
            bucketTaggingConfiguration.setTagSets(tagSetList);

            s3Client.setBucketTaggingConfiguration(new SetBucketTaggingConfigurationRequest(bucketName,bucketTaggingConfiguration));

        }*/

//        s3Client.createBucket(bucketName);

    /*  Uploading objects to S3 bucket

        File file=new File("src/main/resources/note.txt");

        s3Client.putObject(
                bucketName, // bucket name
                "helloworld.txt",  // key to filename in bucket
                file // original file
        );

     */

/*      Read contents of an object in S3 bucket

        S3Object s3Object=s3Client.getObject(bucketName,"helloworld.txt");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(s3Object.getObjectContent()));

        String s=null;

        while((s= bufferedReader.readLine())!=null){
            System.out.println(s);
        }*/

        /*    Empty File:
                s3Client.putObject(
                bucketName,
                "test.txt",
                ""
        );*/

//        Writing again overrides it
//        S3Object s3Object= s3Client.getObject(bucketName,"helloworld.txt");
//        s3Client.putObject(bucketName,"test.txt","HELLO HELLO HELLO");

          /* Write list of POJO into S3 without storing into local file

              S3Object s3Object=s3Client.getObject(bucketName,"helloworld.txt");
              ObjectMapper objectMapper=new ObjectMapper();
    //          byte[] bytesToWrite= objectMapper.writeValueAsBytes(new Person("Vikram",12));
              byte[] bytesToWrite= objectMapper.writeValueAsBytes(List.of(new Person("Vikram",123),new Person("Bedi",123)));


              ObjectMetadata omd=new ObjectMetadata();
              omd.setContentLength(bytesToWrite.length);
              s3Client.putObject(bucketName,"helloworld.txt",new ByteArrayInputStream(bytesToWrite),omd);

          */

        // Different approach
        // IN above approach an array of objects will be formed
        // While here objects will be in a line
        // Or you can separate them by a line

        /*S3Object s3Object=s3Client.getObject(bucketName,"helloworld.txt");
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();

        ObjectMapper objectMapper=new ObjectMapper();
        byte[] person1= objectMapper.writeValueAsBytes(new Person("Vikram",12));
        byte[] person2=objectMapper.writeValueAsBytes(new Person("Bedi",123));

        outputStream.write(person1);
        outputStream.write("\n".getBytes());
        outputStream.write(person2);

        byte[] bytesToWrite=outputStream.toByteArray();


        ObjectMetadata omd=new ObjectMetadata();
        omd.setContentLength(bytesToWrite.length);
        s3Client.putObject(bucketName,"helloworld.txt",new ByteArrayInputStream(bytesToWrite),omd);*/

        // Reading the above written S3 file
        /*S3Object s3Object=s3Client.getObject(bucketName,"helloworld.txt");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(s3Object.getObjectContent()));

        String s;

        while((s= bufferedReader.readLine())!=null){
            System.out.println(s);
        }*/
    }
}
