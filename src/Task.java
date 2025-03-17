import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private Integer id;
    private String description;
    private StatusEnum status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public enum StatusEnum {
        TODO("todo"),
        IN_PROGRESS("in-progress"),
        DONE("done");

        final String value;

        StatusEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static StatusEnum fromValue(String value) {
            for (StatusEnum statusEnum : StatusEnum.values()) {
                if (statusEnum.getValue().equalsIgnoreCase(value)) {
                    return statusEnum;
                }
            }
            return null;
        }
    }

    public Task(Integer id, String description, StatusEnum status, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Task(Integer id, String description) {
        this.id = id;
        this.description = description;
        this.status = StatusEnum.TODO;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(String dateTime) {
        this.createdAt = ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public void setUpdatedAt(String dateTime) {
        this.updatedAt = ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return toJson();
    }

    private String toJson() {
        return "{" + System.lineSeparator() +
                "   \"id\": " + getId() + "," + System.lineSeparator() +
                "   \"description\": \"" + getDescription() + "\"," + System.lineSeparator() +
                "   \"status\": \"" + (getStatus()==null ? null : getStatus().getValue()) + "\"," + System.lineSeparator() +
                "   \"createdAt\": \"" + formatDate(getCreatedAt()) + "\"," + System.lineSeparator() +
                "   \"updatedAt\": \"" + formatDate(getUpdatedAt()) + "\"" + System.lineSeparator() +
                "}";
    }

    private String formatDate(ZonedDateTime date) {
        return date == null ? "null" : date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
