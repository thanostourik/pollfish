namespace java com.tnt.logging.shared

typedef i32 int

enum LogEvent {
    LOG_EVENT_1 = 1,
    LOG_EVENT_2 = 2,
    LOG_EVENT_3 = 3,
}

struct Event {
    1: int v,
    2: string time,
    3: LogEvent m,
    4: string comment
}

service LoggingService
{
    void log(1:Event event)
}