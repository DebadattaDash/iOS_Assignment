Google Keep Appium UI Test Run Results
Date: 2026-10-03
Device: OnePlus CPH2467 (Android 15, serial 17a71cc6)
Appium: http://127.0.0.1:4723/

Passing isolated runs
- createAndDeleteTextNote: PASS (1 test, 0 failures, 0 errors, 44.56 seconds)
- searchForNote: PASS (1 test, 0 failures, 0 errors, 34.59 seconds)

The tests were run separately because a combined invocation had an Appium socket hang-up between test sessions. Each isolated Maven run completed with one test passing.

Not included as a passing test
- editExistingNote remains failing and is not counted in the two passes above.

Commands used:
- mvn -Dmaven.compiler.testCompile.skip=true -Dmaven.repo.local=.m2-cache -Dtest=GoogleKeepFlowsTest#createAndDeleteTextNote --file pom.xml test
- mvn -Dmaven.compiler.testCompile.skip=true -Dmaven.repo.local=.m2-cache -Dtest=GoogleKeepFlowsTest#searchForNote --file pom.xml test
