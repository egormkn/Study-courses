package ru.shemplo.tf.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import java.io.IOException;

import ru.shemplo.dsau.utils.time.TimeDelta;
import ru.shemplo.dsau.utils.time.TimePeriod;
import ru.shemplo.dsau.utils.time.TimeUtils;
import ru.shemplo.tf.ntwk.NetworkSession;
import ru.shemplo.tf.stcs.StatisticsProvider;
import ru.shemplo.tf.stcs.TweetsStatProvider;

public class LocalNetworkSession implements NetworkSession {

	private boolean isConnected = false;
	
	@Override
	public boolean isConnected () {
		return isConnected;
	}

	@Override
	public void tryConnect () throws IOException {
		isConnected = true;
	}

	@Override
	public StatisticsProvider sendRequest (String key, TimePeriod period) throws IOException {
		/*
		DateFormat format = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z YYYY", Locale.ENGLISH);
		List <Pair <Date, Integer>> usages = null;
		try {
			usages = new ArrayList <> (Arrays.asList (
				Pair.mp (format.parse ("Thu Feb 01 16:33:04 +0000 2018"), 23),
				Pair.mp (format.parse ("Fri Feb 02 18:15:27 +0000 2018"), 9),
				Pair.mp (format.parse ("Thu Feb 01 06:45:52 +0000 2018"), 64),
				Pair.mp (format.parse ("Sun Feb 04 09:20:38 +0000 2018"), 119)
			));
		} catch (ParseException pe) {
			pe.printStackTrace ();
		}
		*/
		
		TimePeriod tmpPeriod = TimePeriod.mtp (TimeUtils.floorToDays (period.F), period.S);
		long periodLength = TimeDelta.deltaOfPeriod (tmpPeriod).getLength ();
		List <Date> usages = new ArrayList <> ();
		Random random = new Random ();
		
		for (int i = 0; i < periodLength / 10000; i ++) {
			int delta = random.nextInt ((int) periodLength);
			usages.add (new Date (tmpPeriod.F.getTime () + delta));
		}
		
		return new TweetsStatProvider (key, period, usages);
	}

}