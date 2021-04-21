package org.home.polukeev;

import org.home.polukeev.model.MinerModel;
import org.home.polukeev.model.Repository.Options;
import org.home.polukeev.model.Repository.StatSession;
import org.home.polukeev.model.Repository.Statistics;
import org.home.polukeev.model.runnableUtils.StatisticsLoader;
import org.home.polukeev.model.runnableUtils.StatisticsSaver;
import org.junit.*;

import java.io.File;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by onodee on 21.02.2016.
 * Тесты
 */
public class TestMiner {
    MinerModel minerModel;
    Statistics statistics;

    @BeforeClass
    public static void allTestsStarted() {
        System.out.println("Начало тестирования");
        System.out.println();
    }

    @AfterClass
    public static void allTestsFinished() {
        System.out.println("Все тесты завершены");
    }

    @Before
    public void setUp(){
        System.out.println("Тест запущен");
    }

    @After
    public void testFinished() {
        System.out.println("Тест завершен");
        System.out.println();
    }

    /**
     * Тест формирования игрового поля
     */
    @Test
    public void testAdd001() {
        System.out.println("Тест формирования игрового поля");
        int mineCount10 = 0;
        int mineCount40 = 0;
        int mineCount99 = 0;

        System.out.println("Поле 10х10");
        minerModel = new MinerModel(new Options());
        mineCount10 = calcMineCount();
        System.out.println();

        System.out.println("Поле 16х16");
        minerModel = new MinerModel(new Options(16, 16, 40));
        mineCount40 = calcMineCount();
        System.out.println();

        System.out.println("Поле 16х30");
        minerModel = new MinerModel(new Options(16, 30, 99));
        mineCount99 = calcMineCount();

        assertEquals(10, mineCount10);
        assertEquals(40, mineCount40);
        assertEquals(99, mineCount99);
    }

    private int calcMineCount(){
        int mineCount = 0;
        for (int m = 0; m < minerModel.getRow(); m++) {
            for (int n = 0; n < minerModel.getCol(); n++) {
                if (minerModel.isRigged(m, n)) mineCount++;
                System.out.print(minerModel.getOpenCellStatus(m, n) + " ");
            }
            System.out.println();
        }
        return mineCount;
    }

    /**
     * Тест формирования статистики
     */
    @Test
    public void testAdd002(){
        System.out.println("Тест формирования статистики");
        statistics = new Statistics();
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            statistics.setNewRecord(new StatSession(10, random.nextInt(100), new Date().toString()));
            statistics.setNewRecord(new StatSession(16, random.nextInt(100), new Date().toString()));
            statistics.setNewRecord(new StatSession(30, random.nextInt(100), new Date().toString()));
        }
        for (StatSession statSession : statistics.getStatistics10()){
            System.out.print("Time:" + statSession.getTimer() + ", ");
            System.out.println("Date:" + statSession.getDate());
        }
        assertEquals(10, statistics.getStatistics10().size());
        assertEquals(10, statistics.getStatistics16().size());
        assertEquals(10, statistics.getStatistics30().size());
    }

    /**
     * Тест записи в файл
     */
    @Test
    public void testAdd003(){
        System.out.println("Тест записи в файл");
        statistics = new Statistics();
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            statistics.setNewRecord(new StatSession(10, random.nextInt(100), new Date().toString()));
            statistics.setNewRecord(new StatSession(16, random.nextInt(100), new Date().toString()));
            statistics.setNewRecord(new StatSession(30, random.nextInt(100), new Date().toString()));
        }
        Thread thread = new Thread(new StatisticsSaver(statistics));
        thread.start();
        while (thread.isAlive()){
            try {
                thread.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        File file = new File("MinerJ.stat");
        System.out.println("File isExist: " + file.exists());
        assertNotNull(file.exists());
    }

    /**
     * Тест чтения статистики из файла
     */
    @Test
    public void testAdd004(){
        System.out.println("Тест чтения статистики из файла");
        statistics = null;
        StatisticsLoader loader = new StatisticsLoader();
        Thread thread = new Thread(loader);
        thread.start();
        while (thread.isAlive())
            try {
                thread.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        statistics = loader.getStatistics();
        for (StatSession statSession : statistics.getStatistics10())
            System.out.println("time: " + statSession.getTimer());
        assertNotNull(statistics);
    }

    /**
     * Тест условия проигрыша в игре
     */
    @Test
    public void testAdd005(){
        System.out.println("Тест условия проигрыша в игре");
        minerModel = new MinerModel(new Options());
        int row = 0;
        int col = 0;
        boolean isFind = false;

        System.out.println("isEndGame до мины = " + minerModel.isEndGame());
        for (int y = 0; y < minerModel.getRow(); y++){
            if (isFind) break;
            for (int x = 0; x < minerModel.getCol(); x++){
                if (minerModel.getOpenCellStatus(y, x) == 9){
                    row = y;
                    col = x;
                    isFind = true;
                }
                if (isFind)break;
            }
        }
        minerModel.setOpenCell(row, col);
        System.out.println("isEndGame после мины = " + minerModel.isEndGame());
        assertEquals(0, minerModel.isEndGame());
    }

    /**
     * Тест условия выигрыша в игре
     */
    @Test
    public void testAdd006(){
        System.out.println("Тест условия выигрыша в игре");
        int m = 2;
        int n = 2;
        int mines = 1;
        minerModel = new MinerModel(new Options(m, n, mines));

        System.out.println("isEndGame в начале игры = " + minerModel.isEndGame());
        for (int y = 0; y < m; y++){
            for (int x = 0; x < n; x++){
                if (minerModel.getOpenCellStatus(y, x) != 9)
                    minerModel.setOpenCell(y, x);
            }
        }
        System.out.println("isEndGame в конце игры = " + minerModel.isEndGame());
        assertEquals(1, minerModel.isEndGame());
    }

    /**
     * Тест проверки маркировки ячейки
     */
    @Test
    public void testAdd007(){
        System.out.println("Тест проверки маркировки ячейки");
        minerModel = new MinerModel(new Options());
        int y = 2;
        int x = 2;
        System.out.println("Ячейка (" + y + "," + x +") маркирована? - " + minerModel.isMarked(y, x));
        System.out.println("Выполняю маркировку...");
        minerModel.setMarkCell(y, x);
        System.out.println("Ячейка (" + y + "," + x +") маркирована? - " + minerModel.isMarked(y, x));
        assertTrue(minerModel.isMarked(y, x));
    }

    /**
     * Тест перемещения курсора
     */
    @Test
    public void testAdd008(){
        System.out.println("Тест перемещения курсора");
        Random random = new Random();
        minerModel = new MinerModel(new Options());
        int[] oldCurCoords;                             //[0] - x, [1] - y
        int[] newCurCoords;                             //[0] - x, [1] - y
        int xNewCur = random.nextInt(10);
        int yNewCur = random.nextInt(10);

        oldCurCoords = findCur();
        assertNotNull(oldCurCoords);
        System.out.println("Текущая позиция курсора (" + oldCurCoords[0] + "," + oldCurCoords[1] + ")");
        System.out.println("Перемещаю курсор в позицию (" + xNewCur + "," + yNewCur + ")");
        minerModel.setMove(yNewCur, xNewCur);
        newCurCoords = findCur();
        assertNotNull(newCurCoords);
        System.out.println("Текущая позиция курсора (" + newCurCoords[0] + "," + newCurCoords[1] + ")");
        assertEquals(xNewCur, newCurCoords[0]);
        assertEquals(yNewCur, newCurCoords[1]);

    }

    private int[] findCur(){
        for (int y = 0; y < minerModel.getRow(); y++){
            for (int x = 0; x < minerModel.getCol(); x++){
                if (minerModel.isCursor(y, x))
                    return new int[]{x, y};
            }
        }
        return null;
    }
}
