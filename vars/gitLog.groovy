def committer() {
    sh 'git --no-pager log -1 --pretty=format:"%an" > GIT_COMMITTER'
    result=readFile('GIT_COMMITTER')
    sh 'rm GIT_COMMITTER'
    result
}

def email() {
    sh 'git --no-pager log -1 --pretty=format:"%ae" > GIT_COMMITTER_EMAIL'
    result=readFile('GIT_COMMITTER_EMAIL')
    sh 'rm GIT_COMMITTER_EMAIL'
    result
}
