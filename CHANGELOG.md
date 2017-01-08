## [v3.27-2016j] not yet released
### Added
- Support for Breton (br), Faroese (fo), Western Frisian (fy), Scottish Gaelic (gd), Luxembourgish (lb) [#599]
- Reduce buffer allocation when printing numbers [#598]

### Fixed
- ChronoFormatter.formatToBuffer() should be optimized [#597]

## [v3.26-2016j] published on 2017-01-03
### Added
- Enable or-logic when parsing durations [#592]
- Ensure 4 digit-years in date style patterns [#591]
- Dozenal numbering system [#596]
- Document how to handle wrong platform tz-data on Android [#589]
- Make styled formatters sensible for change of locale [#586]

### Fixed
- Max-calculation for week-of-year sometimes broken [#595]
- RFC-1123-formatter must support northamerican zones [#594]
- MultiFormatParser tolerates trailing characters [#590]
- BridgeChronology: Some format patterns are not runnable [#588]
- BridgeChronology: Some formatters cannot be built [#587]

## [v3.25-2016j] published on 2016-12-02
### Changed
- Simplify parsing of trailing characters [#576]
- Update TZDB-repository to 2016j [#579]
- Use Android-context directly for resource access [#582]

### Fixed
- Error receiving broadcast Intent [#585]
- Fix for pattern sanity check [#583]
- Regression: Misleading error message [#581]
- Clarify documentation of AdjustableElement.atFloor() and .atCeiling() [#580]
- Ambivalence parsing check sometimes faulty [#577]
- Unicode-BIDI-chars should not be parsed in context of ISO [#574]

## [v3.24-2016i] published on 2016-11-03
### Changed
- Update TZDATA to version 2016i [#573]

## [v3.24-2016h] published on 2016-10-23
### Added
- Open ChronoFormatter for external types [#556]
- Week calculations for non-ISO calendars [#562]
- Add convenient methods to PrettyTime [#559]

### Fixed
- Years in far future should not require a positive sign [#569]
- Document pattern order in case of or-logic applied [#558]

### Changed
- Update TZDATA to version 2016h [#564]
- Update resources to CLDR v30.0.2 [#570]
- Add simple sanity checks for cldr-format patterns [#565]

## [v3.23-2016g] published on 2016-09-23
### Added
- Add historic centuries [#507]
- Add some alternative numbering systems [#550]
- Add convenient methods to determine current system time [#553]

### Fixed
- NPE in Timezone.getDisplayName(...) [#548]
- Improved literal parsing in localized tz-offset [#549]
- Byte-order-marks in UTF8-resources not recognized [#551]

### Changed
- New method in ZoneNameProvider-interface [#549]
- Update TZDATA to version 2016g [#554]

## [v3.22.1-2016f] published on 2016-08-28
### Added
- Improved documentation of HijriCalendar [#518]
- Add general parse methods for dates in ISO-8601-format [#520]
- Iso8601Format....WALL_TIME now understands T17:45 [#521]
- Embedded formatter should know outer format attributes and defaults [#522]
- ChronoFormatter.Builder should define default values and global attributes [#531]
- Determine first moment for given calendar date in time zone [#539]
- Enable parsing of literals with leading digits after numerical elements [#544]

### Fixed
- ISO ordinal date format not correct for big year numbers [#530]
- AM/PM-element not sensible for output context [#529]
- Inconsistent exception handling of with()-methods [#541]

### Changed
- Make fractional second parser more tolerant [#519]
- TextProvider-SPI-interface with new method [#529]

### Deprecated
- 1 method in TextProvider deprecated due to missing output context [#529]
- 1 method in TransitionHistory marked as deprecated (for future migration) [#523]

## [v3.22-2016f] published on 2016-07-06
### Added
- Add localized display names for `ChronoElement` [#515]

### Changed
- Update TZDATA to version 2016f #[516]

## [v3.21-2016e] published on 2016-06-20
### Added
- Better support for week-based durations and units [#509]

### Fixed
- Duration measured in millis only is broken [#513]
- Zone offset without sign - should be parseable in lax mode [#502]

### Changed
- Update TZDATA to version 2016e #[512]

## [v3.20-2016d] published on 2016-05-07
### Added
- Related gregorian year for non-gregorian calendars [#370]
- Modern-coverage of languages defined in CLDR [#367]
- Support for Uyghur [#496]
- Support for Malayalam [#493]
- Support for Basque, Galician and Welsh [#494]
- Support for Esperanto [#495]

### Fixed
- Make alias zone identifiers always parseable [#500]
- Improve performance of parsing timezone names [#499]
- Clarify format behaviour if zoneless types are combined with zone names [#490]

### Changed
- Replace `ZoneProvider` by `ZoneModelProvider` and `ZoneNameProvider` [#498]
- Update to CLDR version 29 [#492]

## [v3.19-2016d] published on 2016-04-19
### Changed
- Update TZDB-repository to 2016d [#484]

## [v3.19-2016c] published on 2016-04-18
### Added
- Add Thai solar calendar (Suriyakati) [#478]
- Fine-tuning of historization [#479]
- Document that PlainDate is historizable [#448]

### Fixed
- 3.18-2016c appears to make testing difficult (Android) [#483]
- OR-operator logic in ChronoFormatter sometimes broken [#482]
- Fix and improve documentation of era manipulations [#481]

### Deprecated
- Deprecate usage of CalendarEra.getValue() [#480]

## [v3.18-2016c] published on 2016-03-23
### Status
- Not reliable during start, use later version instead [#483]

### Added
- Starting Time4A as background process [#475]
- Enable unparseable characters to be skipped when parsing [#476]
- Add SQL-support (from misc-module of Time4J) [#471]
- Historic year definition (example Easter style in France) [#473]
- Support for Kirghiz [#474]

### Changed
- Update TZDB-repository to version 2016c [#477]

## [v3.17-2016b] published on 2016-03-13
### Added
- More detailed error message when parsing ZonalDateTime [#467]

### Fixed
- Make resolving zone names more robust [#469]
- Calculus Pisanus does not work in parsing [#466]

### Changed
- Update TZDB-repository to 2016b [#468]

## [v3.16-2016a] published on 2016-03-07
### Added
- Make ZonalDateTime easier for creation and comparison [#462]
- Calculation of Easter (Computus) [#460]
- Support for Telugu language [#459]
- Add element support for historic day-of-year [#456]
 
### Fixed
- TimezoneRepositoryProviderSPI throws NPE [#464]
- Refine localized calendar history in Europe [#461]
- PlainTime.toString() does not display fraction of second as per spec [#465]
- Make Timezone.ofSystem() more robust against weird zone ids. [#463]
- Don't permit negative years for any historic era [#458]
- Clarify/correct exception behaviour of new year strategies [#457]
- Reduce array allocation during parsing [#455]

## [v3.15-2016a] published on 2016-02-14
### Added
- Proleptic Julian calendar [#444]
- Add Roman numerals [#443]

### Fixed
- Unit-between-arithmetic broken for Persian, Coptic and Ethiopian calendar [#453]
- Improve overall performance including two new methods in low-level interfaces [#450]
- Text resources for am-pm are mismatched (in non-iso-calendars) [#452]
- Add equals/hashCode-support for platform formatter [#451]
- Prohibit use of Ethiopian hour with PlainTime [#449]
- Code example in documentation of MultiFormatParser is incomplete [#446]

### Changed
- Update TZDB-repository to 2016a [#447]

## [v3.14-2015g] published on 2016-01-22
### Added
- Two new approximation normalizers based on max unit only [#442]
- Add pattern-based factory method to ChronoFormatter for any chronology [#441]
- Introduce or-operator in formatting via builder and pattern [#437}
- Add other historic eras [#436]
- Support for various historical New Year events [#434]
- Date arithmetic on HijriCalendar [#429]
- New methods `withVariant(...)` on CalendarVariant [#428]
- Dual parsing of embedded or standalone formats [#427]
- Dedicated MultiFormatParser [#426]
- Support for Indian languages gu, kn, mr, pa, ta [#439]
- Support for Burmese (my - Myanmar) [#438]

### Fixed
- Setting proleptic julian history on formatter is ignored [#435]
- Semantic of changing historic era in ChronoHistory unclear [#430]

## [v3.13-2015g] published on 2015-12-31
### Added
- Fixed and flexible day periods with appropriate translations [#369]
- Android: Be aware of user preference for 12/24-hour-format [#424]
- Minguo calendar (Taiwan) [#390]
- Enable localized digits in timezone offsets [#411]
- New getters for day-of-week and day-of-year in PlainDate [#416]
- Query parsed raw data for any registered elements [#415]
- New method CalendarText.getIsoInstance(Locale) [#410]
- New methods CalendarText.patternForXYZ(...) [#410]
- Support for Nepali [#422]
- Support for Mongolian [#421]
- Support for Afrikaans and Zulu [#420]
- Support for the languages Khmer and Lao [#419]

### Fixed
- Android: Make app initialization thread-safe [#423]
- PersianCalendar in Farsi language should be with arabext numbers [#425]
- Bidi literals (LRM, RLM, ALM) should be ignored in parsing [#418]
- Formatting Ethiopian time with AM/PM-marker is not in western style [#413]
- Parsing of Ethiopic tabot names broken if not at end of text [#412]

### Deprecated
- EthiopianCalendar.getYearOfEra() deprecated and renamed to getYear() [#417]
- CalendarText.getGMTPrefix [#410]
- CalendarText.getFormatPatterns() [#410]
- CalendarText.getTimestampPattern [#410]

## [v3.12-2015g] published on 2015-11-30
### Added
- Support for Bengali [#409]
- Ethiopian time should support element PlainTime.COMPONENT [#408]

### Fixed
- HijriAlgorithm calendar variant broken [#407]
- Broken javadoc links to some serial forms [#406]
- British date format should be in order DMY [#403]

### Removed
- Tidy deprecated elements [#404]

## [v3.11-2015g] published on 2015-11-22
### Added
- Support for Sinhalese [#402]
- Add Ethiopian calendar and time [#389]
- Enable formatting/parsing moments using another calendar [#399]
- GeneralTimestamp should implement ChronoDisplay [#401]
- Add Coptic calendar [#388]
- Support for triennal julian leap years [#393]

### Fixed
- Ensure that parsing raw data does not resolve anything [#400]
- Compile problem with text elements in ChronoFormatter.Builder [#398]
- plus/minus(CalendarDays) does not document any exception [#397]
- Arithmetic overflow in ChronoHistory [#391]

### Changed (updated/removed/deprecated)
- New method ChronoMerger.getDefaultStartOfDay() [#399]
- Refactor handling of two exotic format attributes [#395]
- Hide internal format attributes of ChronoHistory [#394]

## [v3.10-2015g] published on 2015-10-22
### Added
- New style-based factory methods for ChronoFormatter [#376], [#377]
- Repository for localized date-time-patterns [#100]
- Support for Amharic (am) [#387]
- Support for Swahili (sw) [#386]
- Support for Uzbek (uz), Turkmen (tk) and Kazakh (kk) [#385]
- Support for Armenian (hy), Azerbaijani (az) and Georgian (ka) [#384]
- Support for Filipino (Tagalog - fil or tl) [#380]
- Support for Icelandic (is) and Malta language (mt) [#375]

### Fixed
- Improved parsing of MSK timezone name [#381]
- Negative duration in Arabic sometimes without minus sign [#379]

### Changed
- Better translations for various countries (mainly arabic and french) [#383]
- Better translations for Spanish locales in Latin America [#368]
- Low-level interface ChronoMerger with new method [#377]
- Review plural rules [#366]
- Update i18n-data to CLDR-28 [#374]
- Change ISO-8601-fallback-mechanism to CLDR standard [#373]
- FormatPatternProvider.getDateTimePattern() with 3 args [#100]

### Removed
- FormatPatternProvider.DEFAULT [#100]

## [v3.9-2015g] published on 2015-10-03
### Changed
- Update to TZDB-version 2015g

## [v3.9-2015f] published on 2015-10-01
### Added
- New Persian calendar [#357]
- Support for Pashto language [#358]
- Support for Turkish islamic calendar (Diyanet) [#355]

### Fixed
- Non-overridden format attributes should be preserved [#364]
- Enable parsing of timestamps with 24:00 even in strict mode [#363]
- 2015-10-25T02:00+01:00[Europe/Berlin] cannot be parsed [#361]
- Wrong between-calculation with overflow units [#360]
- Pattern letter "L" causes exception for non-iso-dates [#354]

### Changed
- Make serialization of HijriCalendar versionable [#356]

### Removed
- Interface ExtZoneProvider (no usage) [#371]

## [v3.8-2015f] published on 2015-09-15
### Added
- Support for Malay language [#353]
- Unify CalendarVariant and Calendrical by new interface CalendarDate [#350]
- Make PatternType.CLDR useable for non-iso-dates [#348]
- New calendar-related class GeneralTimestamp [#340]

### Fixed
- Disable parsing of ambivalent text forms [#351]

### Changed
- Rename unit pattern resource file names [#352]
- CalendarVariant and Calendrical changed Temporal signature [#350]
- Two specialized now()-methods in ZonalClock changed the return type [#340]

## [v3.7-2015f] published on 2015-09-03
### Added
- PrettyTime.printRelativeOrDate() using PlainDate [#343]
- Moment-class with a new precision element [#342]

### Fixed
- Fix for generics-related problem in PatternType [#346]

## [v3.6-2015f] published on 2015-08-31
### Added
- Support for algorithmic variants of islamic calendar [#334]
- Create direct option to determine length of Hijri month or year [#337]
- Add toDate() and toTime() in PlainTimestamp [#336]
- New method(s) for printing durations with 1 parameter only [#333]
- Create option to either print relative time or normal date-time [#328]
- Print relative times with abbreviations [#327]
- I18n-support for "yesterday", "today", "tomorrow" [#310]

### Fixed
- Printing relative time near a leap second incorrect [#332]
- Behaviour of timezone-offset-parser inconsistent with spec [#330]
- Time-axis-element not fully supported [#329]

### Changed
- ISO-formatters need to be strict by default [#331]

## [v3.5-2015f] published on 2015-08-19
- Initial release as AAR-library using the code base of Time4J-v3.5 and timezone data 2015f.
