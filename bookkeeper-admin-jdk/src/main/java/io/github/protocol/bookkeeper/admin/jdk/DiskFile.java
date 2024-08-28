package io.github.protocol.bookkeeper.admin.jdk;

public class DiskFile {
    private String[] indexFiles;
    private String[] journalFiles;
    private String[] entryLogFiles;

    public String[] getIndexFiles() {
        return indexFiles;
    }

    public void setIndexFiles(String[] indexFiles) {
        this.indexFiles = indexFiles;
    }

    public String[] getJournalFiles() {
        return journalFiles;
    }

    public void setJournalFiles(String[] journalFiles) {
        this.journalFiles = journalFiles;
    }

    public String[] getEntryLogFiles() {
        return entryLogFiles;
    }

    public void setEntryLogFiles(String[] entryLogFiles) {
        this.entryLogFiles = entryLogFiles;
    }
}
