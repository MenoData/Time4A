## [v3.8-2015?] not yet released
### Added
- Make PatternType.CLDR useable for non-iso-dates [#348]
- New calendar-related class GeneralTimestamp [#340]

### Changed
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
