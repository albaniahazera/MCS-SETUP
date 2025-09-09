import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Start{
    public static void main(String[] args) {
        String PROGRAM_VERSION = "1.1.0";
        String raw_user_choose;
        String user_choose;
        String minecraft_version;
        Runtime.Version runtime_version = Runtime.version();
        int major_version = runtime_version.feature();

        
        System.out.println("[MCS-SETUP]: Java version: " + runtime_version); 
        System.out.println("[MCS-SETUP]: Java major version: " + major_version);

        if (major_version >= 14) {
            ClearConsole();
            System.out.println("=" .repeat(63));
            System.out.println("\t\t\tMCS-SETUP");
            System.out.println("-" .repeat(63));
            System.out.println("Tools for setup minecraft server automation [BY Albania Hazera]");
            System.out.println("-" .repeat(63));
            System.out.println("\t\t\tVersion: " + PROGRAM_VERSION);
            System.out.println("=" .repeat(63));
            System.out.println("Supported Minecraft server versions:");
            System.out.println("[1] 1.21.1");
            System.out.println("[2] 1.21.2");
            System.out.println("[3] 1.21.3");
            System.out.println("[4] 1.21.4");
            System.out.println("[5] 1.21.5");

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Choose 1 - 5: ");
                raw_user_choose = scanner.nextLine();
                user_choose = raw_user_choose.trim();
                if (user_choose.equals(" ") || user_choose.isEmpty()) {
                    System.out.println("[MCS-SETUP]: Invalid input. Please enter a valid Minecraft version.");
                    System.out.println("[MCS-SETUP]: Program will exit.\n");
                }else if (user_choose.equals("1")) {
                    minecraft_version = "1.21.1";
                    ChooseVersion(minecraft_version);
                }else if (user_choose.equals("2")) {
                    minecraft_version = "1.21.2";
                    ChooseVersion(minecraft_version);
                }else if (user_choose.equals("3")) {
                    minecraft_version = "1.21.3";
                    ChooseVersion(minecraft_version);
                }else if (user_choose.equals("4")) {
                    minecraft_version = "1.21.4";
                    ChooseVersion(minecraft_version);
                }else if (user_choose.equals("5")) {
                    minecraft_version = "1.21.5";
                    ChooseVersion(minecraft_version);
                }else {
                    System.out.println("[MCS-SETUP]: Please choose a valid option.");
                    System.out.println("[MCS-SETUP]: Program will exit.\n");
                }
            }
        }else {
            System.out.println("[MCS-SETUP]: Unsupported Java version. You must install java 14 or above for running this program.");
            System.exit(1);
        }
    }

    public static void ChooseVersion(String minecraft_version) {
        String url_eula = "https://aka.ms/MinecraftEULA";
        System.out.println("[MCS-SETUP]: You choose minecraft server " + minecraft_version + ", For continue you must be accept eula: " + url_eula);
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("agree/no: ");
            String user_agree = scanner.nextLine();
            switch (user_agree) {
                case "agree" -> SetupServer(minecraft_version);
                case "no" -> System.out.println("[MCS-SETUP]: Program will exit. You must choose agree for continue the program.\n");
                default -> {
                    System.out.println("[MCS-SETUP]: Invalid input. Please input agree/no");
                    System.out.println("[MCS-SETUP]: Program will exit.\n");
                }
            }
        }
    }

    public static void SetupServer(String minecraft_version) {
        System.out.println("[MCS-SETUP]: Setting up Minecraft server for version: " + minecraft_version);
        String version_folder_name = "Server" + File.separator + "version" + File.separator + minecraft_version;
        File version_folder = new File(version_folder_name);

        if (version_folder.mkdirs()) {
            System.out.println("[MCS-SETUP]: Created directory: " + version_folder.getAbsolutePath());
            DownloadServerFile(minecraft_version, version_folder);
        }else {
            System.out.println("[MCS-SETUP]: Directory already exists.");
        }
    }

    public static void DownloadServerFile(String minecraft_version, File folder_name) {
        System.out.println("[MCS-SETUP]: Preparing download server files for version: " + minecraft_version);
        String[] file_urls = {
            // 1.21.1
            "https://piston-data.mojang.com/v1/objects/59353fb40c36d304f2035d51e7d6e6baa98dc05c/server.jar",
            // 1.21.2
            "https://piston-data.mojang.com/v1/objects/7bf95409b0d9b5388bfea3704ec92012d273c14c/server.jar",
            // 1.21.3
            "https://piston-data.mojang.com/v1/objects/45810d238246d90e811d896f87b14695b7fb6839/server.jar",
            // 1.21.4
            "https://piston-data.mojang.com/v1/objects/4707d00eb834b446575d89a61a11b5d548d8c001/server.jar",
            // 1.21.5
            "https://piston-data.mojang.com/v1/objects/e6ec2f64e6080b9b5d9b471b291c33cc7f509733/server.jar"
        };
        String save_path = folder_name.getPath();
        String file_name = "server.jar";

        switch (minecraft_version) {
            case "1.21.1" -> {
                System.out.println("[MCS-SETUP]: Downloading server file for version 1.21.1...");
                try {
                    DownloadFile(file_urls[0], save_path + File.separator + file_name);
                    System.out.println("\n[MCS-SETUP]: Downloaded server file successfully.");
                    RunFileServer(save_path, file_name);
                }catch (IOException e) {
                    System.out.println("\n[MCS-SETUP]: Failed to download server file: " + e.getMessage());
                }
            }
            case "1.21.2" -> {
                System.out.println("[MCS-SETUP]: Downloading server file for version 1.21.2...");
                try {
                    DownloadFile(file_urls[1], save_path + File.separator + file_name);
                    System.out.println("\n[MCS-SETUP]: Downloaded server file successfully.");
                    RunFileServer(save_path, file_name);
                }catch (IOException e) {
                    System.out.println("\n[MCS-SETUP]: Failed to download server file: " + e.getMessage());
                }
            }
            case "1.21.3" -> {
                System.out.println("[MCS-SETUP]: Downloading server file for version 1.21.3...");
                try {
                    DownloadFile(file_urls[2], save_path + File.separator + file_name);
                    System.out.println("\n[MCS-SETUP]: Downloaded server file successfully.");
                    RunFileServer(save_path, file_name);
                }catch (IOException e) {
                    System.out.println("\n[MCS-SETUP]: Failed to download server file: " + e.getMessage());
                }
            }
            case "1.21.4" -> {
                System.out.println("[MCS-SETUP]: Downloading server file for version 1.21.4...");
                try {
                    DownloadFile(file_urls[3], save_path + File.separator + file_name);
                    System.out.println("\n[MCS-SETUP]: Downloaded server file successfully.");
                    RunFileServer(save_path, file_name);
                }catch (IOException e) {
                    System.out.println("\n[MCS-SETUP]: Failed to download server file: " + e.getMessage());
                }
            }
            case "1.21.5" -> {
                System.out.println("[MCS-SETUP]: Downloading server file for version 1.21.5...");
                try {
                    DownloadFile(file_urls[4], save_path + File.separator + file_name);
                    System.out.println("\n[MCS-SETUP]: Downloaded server file successfully.");
                    RunFileServer(save_path, file_name);
                }catch (IOException e) {
                    System.out.println("\n[MCS-SETUP]: Failed to download server file: " + e.getMessage());
                }
            }
        }
    }

    public static void RunFileServer(String save_path, String file_name) {
        File server_dir = new File(save_path);
        if (server_dir.mkdirs()) {
            System.out.println("[MCS-SETUP]: Created server directory...");
        }else {
            System.out.println("[MCS-SETUP]: Directory already exist.");
        }

        String jar_path = new File(server_dir, file_name).getAbsolutePath();
        System.out.println("[MCS-SETUP]: Preparing execute file server...");

        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", jar_path, "nogui");
            pb.directory(server_dir);
            pb.inheritIO();
            Process process = pb.start();
            System.out.println("[MCS-SETUP]: Execute jar file...");
            System.out.println("\n\t\t\tMINECRAFT OUTPUT, PLEASE WAIT...");
            System.out.println("=".repeat(63) + "\n");
            int exit_code = process.waitFor();
            System.out.println("\n" + ("=".repeat(63)));
            System.out.println("\t\t\tMINECRAFT OUTPUT END\n");
            System.out.println("[MCS-SETUP]: Execute file finished. With exit code: " + exit_code);
            RunServer(server_dir, save_path);
        }catch (IOException e) {
            System.out.println("[MCS-SETUP]: Failed execute jar file: " + e.getMessage());
        }catch (InterruptedException e) {
            System.out.println("[MCS-SETUP]: Process was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public static void RunServer(File server_path, String save_path) {
        String eula_path = server_path.getAbsolutePath() + File.separator + "eula.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(eula_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }catch (IOException e) {
            System.out.println("[MCS-SETUP]: Failed read file eula.txt: " + e.getMessage());
            return;
        }

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("eula=false")) {
                lines.set(i, "eula=true");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eula_path))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("[MCS-SETUP]: EULA has set to true by program.");
            CheckOS(server_path.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("[MCS-SETUP]: Failed writen file eula.txt: " + e.getMessage());
        }
    }

    public static void CheckOS(String save_path) {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.out.println("[MCS-SETUP]: Preparing created batch file for Windows...");
            CreatedBatchFile(save_path);
        }else if (os.contains("nix") || (os.contains("nux")) || (os.contains("mac"))) {
            System.out.println("[MCS-SETUP]: Preparing created shell file for Linux/Mac...");
            CreatedShellFile(save_path);
        }else {
            System.out.println("[MCS-SETUP]: Unsupported operating system. This program supports Windows, Linux, and Mac.");
            System.exit(1);
        }
    }

    public static void CreatedBatchFile(String run_path) {
        String file_path = run_path + File.separator + "start_server.bat";
        String run_dir = run_path;
        String content = """
            @echo off
            echo Starting Minecraft server...
            java -Xmx1024M -Xms1024M -jar .\\server.jar nogui
            pause
        """;

        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write(content);
            System.out.println("[MCS-SETUP]: File [" + file_path + "] created successfully.");
            System.out.println("[MCS-SETUP]: To start the server, run the following command: ");
            System.out.println("[MCS-SETUP]: cd " + run_dir);
            System.out.println("[MCS-SETUP]: Type -> [.\\start_server.bat] and enter\n");
        }catch (IOException e) {
            System.out.println("[MCS-SETUP]: Failed create file [" + file_path + "]" + e.getMessage());
        }
    }

    public static void CreatedShellFile(String run_path) {
        String file_path = run_path + File.separator + "start_server.sh";
        String run_dir = run_path;
        String content = """
            #!/bin/bash
            echo Starting Minecraft server...
            java -Xmx1024M -Xms1024M -jar ./server.jar nogui
        """;

        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write(content);
            System.out.println("[MCS-SETUP]: File [" + file_path + "] created successfully.");
            // Make the shell script executable
            ProcessBuilder pb = new ProcessBuilder("chmod", "+x", file_path);
            Process process = pb.start();
            try {
                process.waitFor();
                System.out.println("[MCS-SETUP]: File [" + file_path + "] has been made executable.");
                System.out.println("[MCS-SETUP]: To start the server, run the following command: ");
                System.out.println("[MCS-SETUP]: cd " + run_dir);
                System.out.println("[MCS-SETUP]: Type -> [./start_server.sh] and enter\n");
            } catch (InterruptedException e) {
                System.out.println("[MCS-SETUP]: The process was interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }

        }catch (IOException e) {
            System.out.println("[MCS-SETUP]: Failed create file [" + file_path + "]" + e.getMessage());
        }
    }

    public static void DownloadFile(String file_url, String save_path) throws IOException {
        URL url = new URL(file_url);
        URLConnection connection = url.openConnection();

        long total_size = connection.getContentLengthLong();
        long downloaded_size = 0;
        int last_progress = 0;

        try (InputStream inputStream = connection.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(save_path)
        ) 
        {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                downloaded_size += bytesRead;
                if (total_size > 0) {
                    int current_progress = (int) ((downloaded_size * 100) / total_size);
                    if (current_progress > last_progress) {
                        System.out.print("\r[MCS-SETUP]: Downloading... " + current_progress + "%");
                        last_progress = current_progress;
                    }
                }
            }
        }
    } 

    public static void ClearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (java.io.IOException e) {
            System.out.println("[MCS-SETUP]: An IO error occurred while trying to clear the console.");
        } catch (InterruptedException e) {
            System.out.println("[MCS-SETUP]: The console clearing process was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}