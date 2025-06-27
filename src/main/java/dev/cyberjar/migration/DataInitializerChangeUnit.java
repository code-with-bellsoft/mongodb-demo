package dev.cyberjar.migration;

import dev.cyberjar.entity.Civilian;
import dev.cyberjar.entity.Implant;
import dev.cyberjar.entity.ImplantMonitoringLog;
import io.mongock.api.annotations.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

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
        implants.add(new Implant(1L, "ocular", "Model-fXX373", "1.2", "NeuroCore", 617, "447327", "2023-07-03"));
        implants.add(new Implant(2L, "cardiac", "Model-OMt936", "1.1", "SynthForge", 141, "905785", "2024-09-06"));
        implants.add(new Implant(3L, "limb", "Model-Yjx053", "3.8", "MechaMed", 490, "984050", "2024-02-11"));
        implants.add(new Implant(4L, "cardiac", "Model-mUw025", "2.8", "MechaMed", 415, "226330", "2023-07-04"));
        implants.add(new Implant(5L, "ocular", "Model-mZd159", "1.9", "SynthForge", 664, "624181", "2023-10-16"));
        implants.add(new Implant(6L, "limb", "Model-gOq543", "3.8", "SynthForge", 746, "806846", "2024-01-17"));
        implants.add(new Implant(7L, "cardiac", "Model-Gkf965", "2.3", "NeuroCore", 289, "377195", "2024-05-29"));
        implants.add(new Implant(8L, "ocular", "Model-BCf487", "1.7", "MechaMed", 124, "629496", "2024-05-29"));
        implants.add(new Implant(9L, "cardiac", "Model-ooV123", "1.7", "MechaMed", 103, "283686", "2023-11-24"));
        implants.add(new Implant(10L, "cardiac", "Model-lkh474", "3.0", "SynthForge", 197, "941730", "2024-02-14"));
        implants.add(new Implant(11L, "ocular", "Model-zNd426", "1.5", "NeuroCore", 816, "566493", "2024-05-07"));
        implants.add(new Implant(12L, "cardiac", "Model-StO778", "2.3", "NeuroCore", 459, "107741", "2024-06-21"));
        implants.add(new Implant(13L, "limb", "Model-VVo800", "3.8", "NeuroCore", 817, "893238", "2024-12-07"));
        implants.add(new Implant(14L, "limb", "Model-Dvb688", "2.2", "MechaMed", 536, "742669", "2025-03-21"));
        implants.add(new Implant(15L, "ocular", "Model-SiT679", "1.5", "MechaMed", 434, "306310", "2025-06-08"));
        implants.add(new Implant(16L, "limb", "Model-Jtv413", "1.3", "MechaMed", 536, "470917", "2025-04-03"));


        List<Civilian> civilians = new ArrayList<>();
        civilians.add(new Civilian(1L, "Aarav Das", "Ni-96751543-BP", "1965-05-02", true, false, List.of(implants.get(0), implants.get(1))));
        civilians.add(new Civilian(2L, "Paula Lin", "NP-59909166-Wg", "1998-11-01", false, false, List.of(implants.get(2), implants.get(3))));
        civilians.add(new Civilian(3L, "Aelita Fang", "gQ-01247486-nk", "1989-12-01", true, false, List.of(implants.get(4), implants.get(5))));
        civilians.add(new Civilian(4L, "Talon Minx", "Ww-33252326-jv", "1996-11-01", true, false, List.of(implants.get(6), implants.get(7), implants.get(8))));
        civilians.add(new Civilian(5L, "Felicia Lee", "dJ-71032254-JQ", "1973-04-05", false, false, List.of(implants.get(9))));
        civilians.add(new Civilian(6L, "Yllo Hill", "Ew-42902984-rX", "2001-11-16", false, true, List.of(implants.get(10))));
        civilians.add(new Civilian(7L, "Nicholas Ramirez", "Zy-82483905-hw", "1999-06-01", true, false, List.of(implants.get(11), implants.get(12))));
        civilians.add(new Civilian(8L, "Rin Morse", "fI-88901036-kD", "1985-08-01", true, false, List.of(implants.get(13))));
        civilians.add(new Civilian(9L, "Heather Huang", "YD-99086969-CP", "1994-04-16", false, true, List.of(implants.get(14))));
        civilians.add(new Civilian(10L, "Amir Morgan", "MP-66879496-vg", "1975-06-26", false, true, List.of(implants.get(15))));

        mongoTemplate.insert(civilians, Civilian.class);

        String implantSerialNum = implants.get(0).getSerialNumber();
        String civilianNationalId = civilians.get(0).getNationalId();

        List<ImplantMonitoringLog> logs = new ArrayList<>();
        logs.add(new ImplantMonitoringLog("9977f147-af6c-4ca8-879d-9d311c540668", implantSerialNum, civilianNationalId, "2025-06-26T03:05:38.006989", 421.48, 14.12, 1.15));
        logs.add(new ImplantMonitoringLog("09100f89-d062-4558-b5be-c177b2ab9c3b", implantSerialNum, civilianNationalId, "2025-06-26T05:56:38.006989", 124.73, 79.08, 3.9));
        logs.add(new ImplantMonitoringLog("f505df61-0e4b-4222-9f1a-53e577501bf3", implantSerialNum, civilianNationalId, "2025-06-26T07:15:38.006989", 143.51, 76.96, 6.52));
        logs.add(new ImplantMonitoringLog("34b5d69c-3474-4b05-a72e-ea4b8b5be796", implantSerialNum, civilianNationalId, "2025-06-26T12:41:38.006989", 125.94, 58.69, 3.62));
        logs.add(new ImplantMonitoringLog("f1f7127d-7785-4713-a8e8-96d37c39175a", implantSerialNum, civilianNationalId, "2025-06-26T00:55:38.006989", 309.36, 51.13, 3.3));
        logs.add(new ImplantMonitoringLog("c43a4396-c049-40e7-95b1-a0393e455919", implantSerialNum, civilianNationalId, "2025-06-26T07:14:38.006989", 119.61, 84.05, 5.88));
        logs.add(new ImplantMonitoringLog("b8adcdeb-305e-4250-9692-a735b7a9a790", implantSerialNum, civilianNationalId, "2025-06-26T01:42:38.006989", 440.16, 62.4, 2.69));
        logs.add(new ImplantMonitoringLog("d62a515d-2ec4-4617-be30-6104b38380b4", implantSerialNum, civilianNationalId, "2025-06-26T01:10:38.006989", 427.91, 46.43, 8.55));
        logs.add(new ImplantMonitoringLog("1161079d-2d8d-47c0-99a6-589d36f99a09", implantSerialNum, civilianNationalId, "2025-06-26T07:05:38.006989", 316.11, 32.31, 3.38));
        logs.add(new ImplantMonitoringLog("06ae75a9-a217-416b-8aeb-2301046146b0", implantSerialNum, civilianNationalId, "2025-06-26T01:52:38.006989", 437.28, 79.85, 4.8));
        logs.add(new ImplantMonitoringLog("bf32c7d6-dbf5-409e-8cc7-b08ea3720607", implantSerialNum, civilianNationalId, "2025-06-26T03:02:38.006989", 492.74, 54.11, 6.71));
        logs.add(new ImplantMonitoringLog("723f40a9-af19-44dd-a1d0-ace47a067aa0", implantSerialNum, civilianNationalId, "2025-06-26T03:51:38.006989", 222.07, 21.01, 9.65));
        logs.add(new ImplantMonitoringLog("81e24046-61c2-4db0-ab37-795d6d530798", implantSerialNum, civilianNationalId, "2025-06-26T09:51:38.006989", 396.86, 43.64, 6.03));
        logs.add(new ImplantMonitoringLog("4ef8d350-648b-4a24-82a6-f979dfddee78", implantSerialNum, civilianNationalId, "2025-06-26T10:37:38.006989", 343.89, 84.0, 4.48));
        logs.add(new ImplantMonitoringLog("6f0ff254-c7a2-40eb-958b-6cf3bc579cb0", implantSerialNum, civilianNationalId, "2025-06-26T07:20:38.006989", 194.71, 79.83, 7.9));
        logs.add(new ImplantMonitoringLog("99ad5344-80aa-4b20-a770-50431e17f14c", implantSerialNum, civilianNationalId, "2025-06-26T06:00:38.006989", 251.54, 51.96, 9.74));
        logs.add(new ImplantMonitoringLog("7b29a9e5-1c8f-4744-b6cd-00e62dd9d46a", implantSerialNum, civilianNationalId, "2025-06-26T06:49:38.006989", 439.83, 82.24, 6.03));
        logs.add(new ImplantMonitoringLog("44edc50e-4e77-49f6-9caa-68f319c0edc1", implantSerialNum, civilianNationalId, "2025-06-26T08:29:38.006989", 211.95, 30.57, 0.58));
        logs.add(new ImplantMonitoringLog("051e9735-a917-4969-a75b-55ec03d7e8db", implantSerialNum, civilianNationalId, "2025-06-26T03:58:38.006989", 116.52, 54.61, 6.08));
        logs.add(new ImplantMonitoringLog("590ea371-d043-43e3-be5e-388e878dd505", implantSerialNum, civilianNationalId, "2025-06-26T01:34:38.006989", 119.07, 83.08, 3.98));
        logs.add(new ImplantMonitoringLog("64056322-ba9b-4a3a-aa69-234a20245c91", implantSerialNum, civilianNationalId, "2025-06-26T11:09:38.006989", 181.41, 89.37, 9.53));
        logs.add(new ImplantMonitoringLog("a7da96c0-5668-48b0-a3ac-5a39e23b48fe", implantSerialNum, civilianNationalId, "2025-06-26T04:47:38.006989", 425.35, 52.64, 0.56));
        logs.add(new ImplantMonitoringLog("80910d8b-6819-4f2e-939f-779fee573b5b", implantSerialNum, civilianNationalId, "2025-06-26T06:55:38.006989", 441.5, 63.69, 9.48));
        logs.add(new ImplantMonitoringLog("2784bd5e-11b8-4498-b403-41f952573dcb", implantSerialNum, civilianNationalId, "2025-06-26T07:02:38.006989", 378.69, 53.31, 6.6));
        logs.add(new ImplantMonitoringLog("fca48f53-3500-42d6-a0ee-338cf5d1ea02", implantSerialNum, civilianNationalId, "2025-06-26T12:18:38.006989", 224.78, 81.4, 8.39));
        logs.add(new ImplantMonitoringLog("cae12319-4f0e-4372-aa1e-fdfd33f23583", implantSerialNum, civilianNationalId, "2025-06-26T06:01:38.006989", 409.87, 89.68, 8.4));
        logs.add(new ImplantMonitoringLog("439962ca-d382-4f7c-b9d1-dfa2f1e00381", implantSerialNum, civilianNationalId, "2025-06-26T12:00:38.006989", 401.3, 74.56, 4.43));
        logs.add(new ImplantMonitoringLog("8abd607d-d6bd-4a02-b378-a04f09227241", implantSerialNum, civilianNationalId, "2025-06-26T09:42:38.006989", 407.65, 46.26, 0.92));
        logs.add(new ImplantMonitoringLog("31551eed-3e58-4c76-b610-440b97f4b8d4", implantSerialNum, civilianNationalId, "2025-06-26T11:15:38.006989", 490.37, 43.57, 8.66));
        logs.add(new ImplantMonitoringLog("aa22aaff-a3ef-4222-b42f-77939a01f2fb", implantSerialNum, civilianNationalId, "2025-06-26T08:20:38.006989", 126.15, 53.77, 2.57));

        mongoTemplate.insert(logs, ImplantMonitoringLog.class);
    }

    @RollbackExecution
    public void rollbackExecution(MongoTemplate mongoTemplate) {

        mongoTemplate.dropCollection("civilians");
        mongoTemplate.dropCollection("implant_logs");
    }


}
