# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)



set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../libs/${ANDROID_ABI})
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.





set(distribution_DIR ${CMAKE_SOURCE_DIR}/libs/)
add_library( avcodec-58
        SHARED
        IMPORTED)
set_target_properties( avcodec-58
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/armeabi-v7a/libavcodec-58.so)

add_library( avfilter-7
        SHARED
        IMPORTED)
set_target_properties( avfilter-7
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/armeabi-v7a/libavfilter-7.so)

add_library( avformat-58
        SHARED
        IMPORTED)
set_target_properties( avformat-58
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/armeabi-v7a/libavformat-58s.so)

add_library( avutil-56
        SHARED
        IMPORTED)
set_target_properties( avutil-56
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/armeabi-v7a/libavutil-56.so)

add_library( swresample-3
        SHARED
        IMPORTED)
set_target_properties( swresample-3
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/armeabi-v7a/libswresample-3.so)

add_library( swscale-5
        SHARED
        IMPORTED)
set_target_properties( swscale-5
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/armeabi-v7a/libswscale-5.so)


set(source_files_dir ${CMAKE_SOURCE_DIR}/src/main/cpp)

set(source_files
        ${source_files_dir}/ffmpeg_jni.cpp
        ${source_files_dir}/ffmpeg.c
#        ${source_files_dir}/cmdutils.c
        )

set(link_lib
        log
        android
        m
        )

add_library(libffmpeg SHARED IMPORTED)
set_target_properties(libffmpeg PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_output_shared_lib}/libffmpeg.so)



#初步生成的ffmpeg动态库地址
set(ffmpeg_output_shared_lib
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/
        CACHE INTERNAL "ffmpeg_output_shared_lib_dir"
        )
#初步生成的ffmpeg头文件
set(ffmpeg_output_include_dir
        ${CMAKE_SOURCE_DIR}/src/cpp/include
        CACHE INTERNAL "ffmpeg_output_include_dir"
        )

set(include_dir
        ${CMAKE_SOURCE_DIR}/src/main/cpp/include/
        )

include_directories(${include_dir})

set(link_lib
        ${link_lib}
        libffmpeg
        )

add_library( # Sets the name of the library.
        ffmpeg_jni

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${source_files}
        )


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

set(CMAKE_C_FLAGS_DEBUG "${cmake_c_flag_debug} -s")
set(CMAKE_CXX_FLAGS_DEBUG "${cmake_cxx_flag_debug} -s")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -stdlib=libc++ -std=c++11 -fPIC -w")

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        ffmpeg_jni
 #       native-lib
 #       avcodec-58
 #       avfilter-7
 #       avformat-58
 #       avutil-56
 #       swresample-3
 #       swscale-5
        # Links the target library to the log library
        # included in the NDK.

        ${log-lib})