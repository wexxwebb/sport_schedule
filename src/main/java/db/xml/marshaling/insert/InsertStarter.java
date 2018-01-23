package db.xml.marshaling.insert;

import db.xml.xmlWrapper.Table;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class InsertStarter implements Runnable{

    private Logger logger = Logger.getLogger(InsertStarter.class);
    private AtomicBoolean play;
    private BlockingQueue<Table> tableList;
    private ExecutorService exec;
    private List<Future<Boolean>> futureList;

    public InsertStarter(AtomicBoolean play, BlockingQueue<Table> tableList, ExecutorService exec,
                         List<Future<Boolean>> futureList) {
        this.play = play;
        this.tableList = tableList;
        this.exec = exec;
        this.futureList = futureList;
    }

    @Override
    public void run() {
        while (play.get()) {
            Iterator<Table> it = tableList.iterator();
            while (it.hasNext()) {
                Table table = it.next();
                if (table.isReady()) {
                    futureList.add(exec.submit(table));
                    it.remove();
                }
            }
            if (tableList.isEmpty()) {
                break;
            } else {
                it = tableList.iterator();
            }
        }
        logger.info("Table insertion started");
    }
}
