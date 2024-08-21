Running multiple instances of the same application in IntelliJ IDEA
1. Create Separate Run Configurations:
   Open IntelliJ IDEA.
   Go to Run > Edit Configurations from the top menu.
   Click the + icon to add a new configuration.
   Select the appropriate configuration type for your application (e.g., Application for a Java app).
   Set up the configuration parameters as needed (e.g., main class, program arguments, etc.).
   In the Program arguments field, add:  --server.port=8081
   Name this configuration something like App Instance 1.
