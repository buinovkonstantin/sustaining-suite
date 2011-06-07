package model.logging;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 6/7/11
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogSink {

    public enum Level {
        ERROR,
        WARNING,
        STATUS,
        PROFILE,
        VERBOSE,
        DEBUG
    }

    public enum Component {
        ClusterComponent("ClusterManager"),
        ProfileComponent("ProfileManager"),
        PoolComponent("PoolComponent"),
        PoolServerComponent("PoolServerManager"),
        GC("GarbageCollectionManager"),
        GCII("GCII"),
        FIE("FullIterationEngineManager"),
        ReplicationAccessModule("ReplicationAccessModule"),
        ReplicationStorageModule("ReplicationStorageModule");

        private String name;

        Component(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum logFilterType {
        message("com.emc.centera.library.logging.filters.MessageFilter"),
        category("com.emc.centera.library.logging.filters.CategoryFilter"),
        level("com.emc.centera.library.logging.filters.LevelFilter"),
        loggerName("com.emc.centera.library.logging.filters.LevelFilter"),
        transactionOrigin("com.emc.centera.library.logging.filters.QQ"),
        transactionId("com.emc.centera.library.logging.filters.QQ"),
        transactionType("com.emc.centera.library.logging.filters.QQ"),
        threadGroup("com.emc.centera.library.logging.filters.QQ"),
        threadName("com.emc.centera.library.logging.filters.QQ"),
        pid("com.emc.centera.library.logging.filters.QQ"),
        cycles("com.emc.centera.library.logging.filters.QQ"),
        timestamp("com.emc.centera.library.logging.filters.QQ");


        private final String className;

        logFilterType(String className) {
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }

    private String fileFolder;
    private String fileName;
    private String fileExt;
    private boolean logHeader;
    private String creationLockFileName;
    public LogSink() {
    }
}
