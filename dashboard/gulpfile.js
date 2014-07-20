var gulp = require('gulp');
var browserify = require('gulp-browserify');
var concat = require('gulp-concat');
var concatCss = require('gulp-concat-css');

gulp.task('browserify', function() {
    gulp.src('src/js/main.js')
        .pipe(browserify({transform: 'reactify'}))
        .pipe(concat('main.js'))
        .pipe(gulp.dest('dist/js'));
});

gulp.task('css', function() {
        gulp.src('src/css/*')
            .pipe(concatCss('css/bundle.css'))
            .pipe(gulp.dest('dist'));
});

gulp.task('copy', function() {
    gulp.src('src/index.html')
        .pipe(gulp.dest('dist'));

    gulp.src('src/images/**/*')
        .pipe(gulp.dest('dist/images'));
});

gulp.task('default', ['browserify', 'css', 'copy']);

gulp.task('watch', function() {
    gulp.watch('src/**/*.*', ['default']);
});
