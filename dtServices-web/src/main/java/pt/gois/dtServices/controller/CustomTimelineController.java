package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

@ManagedBean
@ViewScoped
public class CustomTimelineController implements Serializable {

	private static final long serialVersionUID = 1L;

	private TimelineModel model;
	private String locale; // current locale as String, java.util.Locale is possible too.
	private Date start;
	private Date end;

	@PostConstruct
	protected void initialize() {
		// set initial start / end dates for the axis of the timeline
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		Date now = new Date();

		cal.setTimeInMillis(now.getTime() - 4 * 60 * 60 * 1000);
		start = cal.getTime();

		cal.setTimeInMillis(now.getTime() + 8 * 60 * 60 * 1000);
		end = cal.getTime();

		// groups
		String[] NAMES = new String[] {"Serviço 01", "Serviço 02", "Serviço 03"};

		// create timeline model
		model = new TimelineModel();

		//for (String name : NAMES) {
			now = new Date();
			Date end = new Date(now.getTime() - 12 * 60 * 60 * 1000);
			

			// for (int i = 0; i < 5; i++) {
				Date start = new Date(end.getTime() + Math.round(Math.random() * 5) * 60 * 60 * 1000);
				end = new Date(start.getTime() + Math.round(4 + Math.random() * 5) * 60 * 60 * 1000);

				//long r = Math.round(Math.random() * 2);
				//String availability = (r == 0 ? "Unavailable" : (r == 1 ? "Available" : "Maybe"));
				String availability = "Execução";

				// create an event with content, start / end dates, editable flag, group name and custom style class
				TimelineEvent event = new TimelineEvent(availability, start, end, true, NAMES[0], availability);
				model.add(event);
				
				now = new Date();
				end = new Date(now.getTime() - 12 * 60 * 60 * 1000);
				start = new Date(end.getTime() + Math.round(Math.random() * 5) * 60 * 60 * 1000);
				end = now;
				availability = "Execução";
				event = new TimelineEvent(availability, start, end, true, NAMES[1], availability);
				model.add(event);
				now = new Date();
				end = now;
				start = now;
				end = new Date(now.getTime() + 10000);;
				availability = "Fim";
				event = new TimelineEvent(availability, start, end, true, NAMES[1], availability);
				model.add(event);

				now = new Date();
				end = new Date(now.getTime() - 12 * 60 * 60 * 1000);
				start = new Date(end.getTime() + Math.round(Math.random() * 5) * 60 * 60 * 1000);
				end = new Date(start.getTime() + Math.round(4 + Math.random() * 5) * 60 * 60 * 1000);
				availability = "Execução";
				event = new TimelineEvent(availability, start, end, true, NAMES[2], availability);
				model.add(event);

				
			//}
		//}
	}

	public TimelineModel getModel() {
		return model;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}
}
            