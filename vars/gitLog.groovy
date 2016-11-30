def committer() {
    sh 'git --no-pager log -1 --pretty=format:"%an" > GIT_COMMITTER'
    result=readFile('GIT_COMMITTER')
    sh 'rm GIT_COMMITTER'
    result
}
