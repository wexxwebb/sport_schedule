package db.xml;

import common.Log;
import db.xml.fileFounder.FileFounder;
import db.xml.fileFounder.Founder;
import db.xml.marshaling.TableUnmarshaller;
import db.xml.marshaling.UnmarshalingResult;
import db.xml.marshaling.listener.ListenerAdd;
import db.xml.marshaling.listener.ListenerCheck;
import db.xml.xmlWrapper.Table;
import org.apache.log4j.Logger;

import javax.management.ListenerNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadUnMarsh {

    private static Logger logger = Logger.getLogger(MultiThreadUnMarsh.class);

    public static void main(String[] args) {

        Founder founder = new FileFounder("_xml/", "xml", 1);

        List<String> xmlPathsStringList = founder.getResourcesList();

        List<Future<UnmarshalingResult>> unMarshFutureList = new ArrayList<>();
        ExecutorService unMarshExec = Executors.newFixedThreadPool(xmlPathsStringList.size());

        for (String s : xmlPathsStringList) {
            Path path = Paths.get(s);
            logger.info(path.toFile().getName() + " found");
            String className =
                    Table.class.getPackage().getName() + "." +
                            path.toFile().getName().replace(".xml", "");
            try {
                Class tableClass = Class.forName(className);
                unMarshFutureList.add(unMarshExec.submit(new TableUnmarshaller(path, tableClass)));

            } catch (ClassNotFoundException e) {
                logger.error(new Log("Class not found", className));
                break;
            }
        }
        logger.info("Tables unmarshaling started");

        List<Table> tableList = new ArrayList<>();
        ListenerAdd listenerAdd = new ListenerAdd(tableList);
        ListenerCheck listenerCheck = new ListenerCheck();
        {
            boolean play = true;
            while (play) {
                Iterator<Future<UnmarshalingResult>> it = unMarshFutureList.iterator();
                while (it.hasNext()) {
                    try {
                        Future<UnmarshalingResult> unMarshFuture = it.next();
                        if (unMarshFuture.isDone()) {
                            if (unMarshFuture.get().isSuccess()) {
                                Table table = (Table) unMarshFuture.get().getObject();
                                listenerAdd.add(table);

                                tableList.add(table);
                            }
                            it.remove();
                        }
                    } catch (InterruptedException e) {
                        logger.error(new Log(e, "Thread interrupted"));
                    } catch (ExecutionException e) {
                        logger.error(new Log(e, "Thread execution error"));
                    }
                }
                play = !unMarshFutureList.isEmpty();
            }
        }


        logger.info("Table insertion started");
        unMarshExec.shutdown();

//        ExecutorService insertTableExec = Executors.newFixedThreadPool(unMarshFutureList.size());
//        List<Future<Boolean>> insertionFutureList = new ArrayList<>();
//
//        {
//            boolean play = true;
//            while (play) {
//                Iterator<Future<UnmarshalingResult>> it = unMarshFutureList.iterator();
//                while (it.hasNext()) {
//                    try {
//                        Future<UnmarshalingResult> unMarshFuture = it.next();
//                        if (unMarshFuture.isDone()) {
//                            if (unMarshFuture.get().isSuccess()) {
//                                Table table = (Table) unMarshFuture.get().getObject();
//                                Future<Boolean> future = insertTableExec.submit(table);
//                                insertionFutureList.add(future);
//                            }
//                            it.remove();
//                        }
//                    } catch (InterruptedException e) {
//                        logger.error(new Log(e, "Thread interrupted"));
//                    } catch (ExecutionException e) {
//                        logger.error(new Log(e, "Thread execution error"));
//                    }
//                }
//                play = !unMarshFutureList.isEmpty();
//            }
//        }



//        boolean play = true;
//        while (play) {
//            Iterator<Future<Boolean>> it = insertionFutureList.iterator();
//            while (it.hasNext()) {
//                Future<Boolean> future = it.next();
//                if (future.isDone()) {
//                    try {
//                        logger.info(future.get());
//                    } catch (InterruptedException e) {
//                        logger.error(new Object[]{e, "Thread interrupted"});
//                    } catch (ExecutionException e) {
//                        logger.error(new Object[]{e, "Thread execution error"});
//                    }
//                    it.remove();
//                }
//            }
//            play = !insertionFutureList.isEmpty();
//        }
//        insertTableExec.shutdown();
    }
}
