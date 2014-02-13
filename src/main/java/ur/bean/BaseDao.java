package ur.bean;

public abstract class BaseDao {

    private DataSource dataSource;

    protected BaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}

