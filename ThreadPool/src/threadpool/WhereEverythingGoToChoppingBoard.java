package threadpool;

public class WhereEverythingGoToChoppingBoard {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        pool.add(new MissionImpossible());
        pool.add(new MissionDangerous());
        pool.execute();
        pool.execute();
    }
}
