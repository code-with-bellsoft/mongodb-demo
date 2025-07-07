package dev.cyberjar.migration;

import dev.cyberjar.entity.Civilian;
import dev.cyberjar.entity.Implant;
import dev.cyberjar.entity.ImplantMonitoringLog;
import io.mongock.api.annotations.*;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ChangeUnit(id = "data-initializer", order = "001", author = "cyberjar")
public class DataInitializerChangeUnit {

    @BeforeExecution
    public void beforeExecution(MongoTemplate mongoTemplate) {

        mongoTemplate.createCollection("civilians");
        mongoTemplate.createCollection("implant_logs");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(MongoTemplate mongoTemplate) {

        mongoTemplate.dropCollection("civilians");
        mongoTemplate.dropCollection("implant_logs");
    }

    @Execution
    public void seedDatabase(MongoTemplate mongoTemplate) {

        List<Implant> implants = new ArrayList<>();
        implants.add(new Implant("ocular", "Model-fXX373", "1.2", "NeuroCore", 617, "447327", "2023-07-03"));
        implants.add(new Implant("cardiac", "Model-OMt936", "1.1", "SynthForge", 141, "905785", "2024-09-06"));
        implants.add(new Implant("limb", "Model-Yjx053", "3.8", "MechaMed", 490, "984050", "2024-02-11"));
        implants.add(new Implant("cardiac", "Model-mUw025", "2.8", "MechaMed", 415, "226330", "2023-07-04"));
        implants.add(new Implant("ocular", "Model-mZd159", "1.9", "SynthForge", 664, "624181", "2023-10-16"));
        implants.add(new Implant("limb", "Model-gOq543", "3.8", "SynthForge", 746, "806846", "2024-01-17"));
        implants.add(new Implant("cardiac", "Model-Gkf965", "2.3", "NeuroCore", 289, "377195", "2024-05-29"));
        implants.add(new Implant("ocular", "Model-BCf487", "1.7", "MechaMed", 124, "629496", "2024-05-29"));
        implants.add(new Implant("cardiac", "Model-ooV123", "1.7", "MechaMed", 103, "283686", "2023-11-24"));
        implants.add(new Implant("cardiac", "Model-lkh474", "3.0", "SynthForge", 197, "941730", "2024-02-14"));
        implants.add(new Implant("ocular", "Model-zNd426", "1.5", "NeuroCore", 816, "566493", "2024-05-07"));
        implants.add(new Implant("cardiac", "Model-StO778", "2.3", "NeuroCore", 459, "107741", "2024-06-21"));
        implants.add(new Implant("limb", "Model-VVo800", "3.8", "NeuroCore", 817, "893238", "2024-12-07"));
        implants.add(new Implant("limb", "Model-Dvb688", "2.2", "MechaMed", 536, "742669", "2025-03-21"));
        implants.add(new Implant("ocular", "Model-SiT679", "1.5", "MechaMed", 434, "306310", "2025-06-08"));
        implants.add(new Implant("limb", "Model-Jtv413", "1.3", "MechaMed", 536, "470917", "2025-04-03"));


        List<Civilian> civilians = new ArrayList<>();
        civilians.add(new Civilian(null, "Aarav Das", "Ni-96751543-BP", "1965-05-02", true, false, List.of(implants.get(0), implants.get(1))));
        civilians.add(new Civilian(null, "Paula Lin", "NP-59909166-Wg", "1998-11-01", false, false, List.of(implants.get(2), implants.get(3))));
        civilians.add(new Civilian(null, "Aelita Fang", "gQ-01247486-nk", "1989-12-01", true, false, List.of(implants.get(4), implants.get(5))));
        civilians.add(new Civilian(null, "Talon Minx", "Ww-33252326-jv", "1996-11-01", true, false, List.of(implants.get(6), implants.get(7), implants.get(8))));
        civilians.add(new Civilian(null, "Felicia Lee", "dJ-71032254-JQ", "1973-04-05", false, false, List.of(implants.get(9))));
        civilians.add(new Civilian(null, "Yllo Hill", "Ew-42902984-rX", "2001-11-16", false, true, List.of(implants.get(10))));
        civilians.add(new Civilian(null, "Nicholas Ramirez", "Zy-82483905-hw", "1999-06-01", true, false, List.of(implants.get(11), implants.get(12))));
        civilians.add(new Civilian(null, "Rin Morse", "fI-88901036-kD", "1985-08-01", true, false, List.of(implants.get(13))));
        civilians.add(new Civilian(null, "Heather Huang", "YD-99086969-CP", "1994-04-16", false, true, List.of(implants.get(14))));
        civilians.add(new Civilian(null, "Amir Morgan", "MP-66879496-vg", "1975-06-26", false, true, List.of(implants.get(15))));

        mongoTemplate.insert(civilians, Civilian.class);


        String implantSerialNum = implants.getFirst().getSerialNumber();
        String civilianNationalId = civilians.getFirst().getNationalId();

        double powerUsage = 1.5;
        double cpuUsage = 1.0;
        double neuralLatency = 0.5;

        List<ImplantMonitoringLog> logs = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            ImplantMonitoringLog implantMonitoringLog = new ImplantMonitoringLog(null,
                    implantSerialNum, civilianNationalId,
                    LocalDateTime.now().minusHours(i),
                    powerUsage + i,
                    cpuUsage + i,
                    neuralLatency + i,
                    new Point(4.899, 52.372)); //Coordinates for Amsterdam longitude/latitude

            logs.add(implantMonitoringLog);
        }

        mongoTemplate.insert(logs, ImplantMonitoringLog.class);
    }

    @RollbackExecution
    public void rollbackExecution(MongoTemplate mongoTemplate) {

        mongoTemplate.dropCollection("civilians");
        mongoTemplate.dropCollection("implant_logs");
    }

}
