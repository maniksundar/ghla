package com.ghla.library.authority;

public interface ReportContentType {
    // We have modelled the ReportContent to be one of the following types.
    // If any new types are added then the ReportContentViews code need to be edited for support.
    int TYPE_0 = 0;  // Title + Subtitle + Question
    int TYPE_1 = 1;  // Title + Subtitle
    int TYPE_2 = 2;  // Title

    int getType();
}
