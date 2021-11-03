# Merge conflict - cherry pick manually

**The automatic cherry picking didn't work out, please do it manually.**  
First of all don't panic. 
In the following lines all the necessary steps are explained.

For additional information what you are going to do, check out:  
- [Git Documentation - Cherry Pick](https://git-scm.com/docs/git-cherry-pick)  
- [Atlassin Git Cherry Pick](https://www.atlassian.com/git/tutorials/cherry-pick)  

Structure of feature branch name that is created by the MergeBot
**_tmpFromBranchErrorName_**  

Check out this feature branch to your local environment.

- Enter the following Command in Git-Bash:  

  ```
  git cherry-pick _sha1_
  ```     
  If it says: **`fatal: bad object _sha1_`**  
  ```
  git pull
  ```

  try again:
  ```
  git cherry-pick _sha1_  
  ```
- Solve the cherry pick errors marked with ```<<<< >>>>``` on your branch **_tmpFromBranchErrorName_**.  
  
- After solving the merge conflict, crosscheck with `git status` that the changes to be added are not including extra files.  
  WARNING: IF YOU SKIP THIS STEP YOU MIGHT ADD/COMMIT/PUSH UNWANTED FILES.

- When you are sure that the merge conflicts are fixed and no extra file will be added, enter the following commands in Git Bash:  
  ```
  git add .  
  git commit -m "_commitMessage_"  
  git push  
  ```
  WARNING: IF YOU SKIP THE PREVIOUS STEP YOU MIGHT ADD/COMMIT/PUSH UNWANTED FILES. 

- If there is **nothing to commit after solving the merge conflicts** (e.g it is already fixed in the target version), 
  enter the following commands in Git Bash to close the cherry-pick:  
  ```
  git cherry-pick --continue  
  git cherry-pick --skip  
  ```
  In this case, the last step is to close this pull request on Github. 
  Be sure to state a comment why the PR was closed to document the reason and delete the feature branch.

All your changes to **_tmpFromBranchErrorName_** were added to that pull request.  

- Finally, PLEASE **_DELETE THIS FILE WHEN YOU ARE DONE_**. 
  The pull request must only contain your changes.
  ```
  git rm MB_MergeConflictInstructions.deleteMe.md
  git commit -m "rm MergeConflictInstructions"
  git push
  ```
